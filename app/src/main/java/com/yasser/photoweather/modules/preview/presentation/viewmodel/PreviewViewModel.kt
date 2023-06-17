package com.yasser.photoweather.modules.preview.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.yasser.photoweather.base.presentation.viewmodel.StateViewModel
import com.yasser.photoweather.modules.gallery.domain.interactors.GetAllSavedImagesUseCase
import com.yasser.photoweather.modules.gallery.presentation.uimodel.*
import com.yasser.photoweather.modules.main.presentation.uimodel.MainScreensEnum
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUIEvents
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiModel
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiState
import com.yasser.photoweather.modules.preview.presentation.uimodel.PreviewUIEvents
import com.yasser.photoweather.modules.preview.presentation.uimodel.PreviewUiModel
import com.yasser.photoweather.modules.preview.presentation.uimodel.PreviewUiState
import com.yasser.photoweather.modules.preview.presentation.uimodel.toPreviewUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor():
    StateViewModel<PreviewUiModel, PreviewUiState, PreviewUIEvents>(PreviewUiState(localPath = null, isLoading = true)) {
    override fun mapStateToUIModel(
        oldState: PreviewUiState,
        newState: PreviewUiState
    ): PreviewUiModel {
        return newState.localPath.toPreviewUiModel(newState.isLoading)
    }

    override fun sendEvent(event: PreviewUIEvents) {
        when(event){
            is PreviewUIEvents.StartShowImage->getImagePAth(event.localPath)
        }
    }

    private fun getImagePAth(localPath:String?){
        updateState(PreviewUiState(localPath = localPath, isLoading = false))
    }


}