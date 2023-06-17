package com.yasser.photoweather.modules.splash.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.yasser.nyarticles.modules.splash.presentation.uimodel.SplashUiModel
import com.yasser.nyarticles.modules.splash.presentation.uimodel.SplashUiState
import com.yasser.photoweather.base.presentation.viewmodel.StateViewModel
import com.yasser.photoweather.modules.splash.presentation.uimodel.SplashUIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor():
    StateViewModel<SplashUiModel, SplashUiState,SplashUIEvents>(SplashUiState(navigateToNextScreen = false)) {
    override fun mapStateToUIModel(
        oldState: SplashUiState,
        newState: SplashUiState
    ): SplashUiModel {
        return SplashUiModel(
            navigateToNextScreen = newState.navigateToNextScreen
        )
    }



     private fun startSplashDelay(){
        viewModelScope.launch {
            delay(2000)
            updateState(state.copy(navigateToNextScreen = true))
        }
    }

    override fun sendEvent(event: SplashUIEvents) {
        when(event){
            is SplashUIEvents.StartSplashDelay ->startSplashDelay()
        }
    }


}