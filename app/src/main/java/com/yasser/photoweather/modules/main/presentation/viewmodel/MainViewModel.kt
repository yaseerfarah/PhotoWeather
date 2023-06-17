package com.yasser.photoweather.modules.main.presentation.viewmodel

import com.yasser.photoweather.base.presentation.viewmodel.StateViewModel
import com.yasser.photoweather.modules.main.presentation.uimodel.MainScreensEnum
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUIEvents
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiModel
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor():
    StateViewModel<MainUiModel, MainUiState, MainUIEvents>(MainUiState(currentScreen = MainScreensEnum.GALLERY)) {
    override fun mapStateToUIModel(oldState: MainUiState, newState: MainUiState): MainUiModel {
        return MainUiModel(
            currentScreen = newState.currentScreen
        )
    }



     private fun setCurrentScreen(currentScreen: MainScreensEnum){

        updateState(state.copy(currentScreen = currentScreen))

    }


    override fun sendEvent(event: MainUIEvents) {
        when(event){
            is MainUIEvents.NavigateToScreen->setCurrentScreen(event.screen)
        }
    }


}