package com.yasser.photoweather.modules.cameraweather.presentation.uimodel

import com.yasser.photoweather.R
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import java.net.ConnectException
import java.net.UnknownHostException


fun WeatherEntity.toCameraWeatherUiState():CameraWeatherUiState{
    return CameraWeatherUiState(
        data = this,
        isLoading = false,
        errorMsg = null,
        isNetworkError = false,
        imageLocalPath = null
    )
}

fun getUnGrantedPermissionCameraWeatherUiState():CameraWeatherUiState{
    return CameraWeatherUiState(
        data = null,
        isLoading = false,
        errorMsg = R.string.camera_permission,
        isNetworkError = false,
        imageLocalPath = null
    )
}

fun getCameraErrorCameraWeatherUiState():CameraWeatherUiState{
    return CameraWeatherUiState(
        data = null,
        isLoading = false,
        errorMsg = R.string.camera_error,
        isNetworkError = false,
        imageLocalPath = null
    )
}


fun Throwable.toCameraWeatherUiState():CameraWeatherUiState{
    return CameraWeatherUiState(
        data = null,
        isLoading = false,
        errorMsg = this.getErrorMessageResource(),
        isNetworkError = this is ConnectException || this is UnknownHostException,
        imageLocalPath = null
    )
}

fun Throwable.getErrorMessageResource():Int{
    return when(this){
        is ConnectException, is UnknownHostException -> R.string.msgNoInternetConnection
        else -> R.string.msgUnexpextedError
    }
}