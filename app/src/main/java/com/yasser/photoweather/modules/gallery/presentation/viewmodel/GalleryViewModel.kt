package com.yasser.photoweather.modules.gallery.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.yasser.photoweather.base.presentation.viewmodel.StateViewModel
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
class GalleryViewModel @Inject constructor(
    private val getAllSavedImagesUseCase: GetAllSavedImagesUseCase
):
    StateViewModel<GalleryUiModel, GalleryUiState, GalleryUIEvents>(GalleryUiState(data = listOf(), isLoading = true, errorMsg = null)) {
    override fun mapStateToUIModel(
        oldState: GalleryUiState,
        newState: GalleryUiState
    ): GalleryUiModel {
       return GalleryUiModel(
            data = newState.data.toListOfSavedImageUiModel(),
            showLoading = newState.isLoading,
            showUnexpectedError = newState.errorMsg!=null,
            errorMsg = newState.errorMsg,
            showEmptyState = newState.data.isEmpty()&&!newState.isLoading&&newState.errorMsg==null
        )
    }

    override fun sendEvent(event: GalleryUIEvents) {
        when (event){
            is GalleryUIEvents.GetAllSavedImage->getAllSavedImageFromDatabase()
        }
    }


    private fun getAllSavedImageFromDatabase(){
        updateState(GalleryUiState(data = listOf(), isLoading = true, errorMsg = null))
        viewModelScope.launch {
            try {
                val listOfSavedImage = withContext(Dispatchers.IO){ getAllSavedImagesUseCase(Unit)}
                updateState(listOfSavedImage.toGalleryUiState())

            }catch (t:Throwable){
                updateState(t.toGalleryUiState())
            }
        }

    }


}