package com.yasser.photoweather.modules.cameraweather.presentation.uimodel

import androidx.annotation.StringRes
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity


data class CameraWeatherUiModel(
    val location:String,
    val country: String,
    val condition: String,
    val iconUrl:String,
    val temp_c: Double,
    val is_morning: Boolean,
    val isImageSaved:Boolean,
    val imageLocalPath:String?,
    val showLoading:Boolean,
    @StringRes val errorMsg:Int?,
    val showUnexpectedError:Boolean,
    val showNetworkError:Boolean
)
