package com.yasser.photoweather.modules.preview.presentation.uimodel

sealed class PreviewUIEvents{

    data class StartShowImage(val localPath:String?):PreviewUIEvents()

}