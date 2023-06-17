package com.yasser.photoweather.modules.cameraweather.presentation.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.yasser.photoweather.base.presentation.viewmodel.StateViewModel
import com.yasser.photoweather.modules.cameraweather.domain.entity.param.SaveImageBitmapParam
import com.yasser.photoweather.modules.cameraweather.domain.interactors.GetCurrentWeatherUseCase
import com.yasser.photoweather.modules.cameraweather.domain.interactors.SaveImageBitmapUseCase
import com.yasser.photoweather.modules.cameraweather.presentation.uimodel.*
import com.yasser.photoweather.modules.gallery.domain.interactors.GetAllSavedImagesUseCase
import com.yasser.photoweather.modules.gallery.presentation.uimodel.*
import com.yasser.photoweather.modules.main.presentation.uimodel.MainScreensEnum
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUIEvents
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiModel
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CameraWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val savedImagesUseCase: SaveImageBitmapUseCase
):
    StateViewModel<CameraWeatherUiModel, CameraWeatherUiState, CameraWeatherUIEvents>(
        CameraWeatherUiState(data = null, isLoading = true, errorMsg = null,isNetworkError = false, imageLocalPath = null)
    ) {
    override fun mapStateToUIModel(
        oldState: CameraWeatherUiState,
        newState: CameraWeatherUiState
    ): CameraWeatherUiModel {
       return CameraWeatherUiModel(
           location = (newState.data?.location + " , " + newState.data?.country),
           country=newState.data?.country?:"",
           condition=newState.data?.condition?:"",
           iconUrl="https:"+(newState.data?.iconUrl?:""),
           temp_c=newState.data?.temp_c?:0.0,
           is_morning=newState.data?.is_morning?:false,
           showLoading = newState.isLoading,
           showUnexpectedError = newState.errorMsg!=null&&!newState.isNetworkError,
           errorMsg = newState.errorMsg,
           showNetworkError = newState.data==null&&newState.isNetworkError,
           isImageSaved = newState.imageLocalPath!=null,
           imageLocalPath = newState.imageLocalPath
        )
    }

    override fun sendEvent(event: CameraWeatherUIEvents) {
        when (event){
            is CameraWeatherUIEvents.RequestCameraPermission->getCurrentWeather(event.isGranted)
            is CameraWeatherUIEvents.SaveImageBitmap->saveImageBitmap(bitmap = event.bitmap)
            is CameraWeatherUIEvents.CameraInitializationError->updateState(
                getCameraErrorCameraWeatherUiState()
            )
            is CameraWeatherUIEvents.ResetInitialState->updateState(CameraWeatherUiState(data = null, isLoading = true, errorMsg = null,isNetworkError = false, imageLocalPath = null))
        }
    }



    private fun saveImageBitmap(bitmap: Bitmap){
        updateState(state.copy(isLoading = true))

        viewModelScope.launch {
            try {
                val localPath= withContext(Dispatchers.IO){ savedImagesUseCase(SaveImageBitmapParam(bitmap))}
                updateState(state.copy(isLoading = false,imageLocalPath = localPath))
            }catch (t: Throwable){
                updateState(t.toCameraWeatherUiState())
            }

        }

    }

    private fun getCurrentWeather(isGranted:Boolean){
        if (isGranted) {
            updateState(
                CameraWeatherUiState(
                    data = null,
                    isLoading = true,
                    errorMsg = null,
                    isNetworkError = false,
                    imageLocalPath = null
                )
            )
            viewModelScope.launch {
                try {
                    val currentWeather =
                        withContext(Dispatchers.IO) { getCurrentWeatherUseCase(Unit) }
                    updateState(currentWeather.toCameraWeatherUiState())

                } catch (t: Throwable) {
                    updateState(t.toCameraWeatherUiState())
                }
            }
        }else{
            updateState(getUnGrantedPermissionCameraWeatherUiState())
        }

    }


}