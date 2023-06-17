package com.yasser.photoweather.modules.main.presentation.uimodel

sealed class MainUIEvents{

    data class NavigateToScreen(val screen: MainScreensEnum):MainUIEvents()

}