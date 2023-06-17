package com.yasser.photoweather.modules.cameraweather.presentation.uimodel

import androidx.annotation.StringRes
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import com.yasser.photoweather.modules.gallery.domain.entity.SavedImageEntity

data class CameraWeatherUiState(val data:WeatherEntity?,val imageLocalPath:String? ,val isLoading:Boolean, @StringRes val errorMsg:Int?, val isNetworkError:Boolean)
