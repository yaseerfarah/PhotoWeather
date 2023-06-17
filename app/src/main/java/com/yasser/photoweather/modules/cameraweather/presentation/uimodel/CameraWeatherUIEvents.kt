package com.yasser.photoweather.modules.cameraweather.presentation.uimodel

import android.graphics.Bitmap

sealed class CameraWeatherUIEvents{

    data class RequestCameraPermission(val isGranted:Boolean):CameraWeatherUIEvents()
    data class SaveImageBitmap(val bitmap: Bitmap):CameraWeatherUIEvents()
    object CameraInitializationError:CameraWeatherUIEvents()
    object ResetInitialState:CameraWeatherUIEvents()

}