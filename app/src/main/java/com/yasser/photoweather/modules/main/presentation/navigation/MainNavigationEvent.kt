package com.yasser.photoweather.modules.main.presentation.navigation


sealed class  MainNavigationEvent {

    object BackToGalleryScreen: MainNavigationEvent()
    data class NavigateToPreviewScreen(val localPath:String): MainNavigationEvent()
    object NavigateToCameraWeatherScreen: MainNavigationEvent()



}