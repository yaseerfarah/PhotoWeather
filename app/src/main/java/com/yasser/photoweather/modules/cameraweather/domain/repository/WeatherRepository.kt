package com.yasser.photoweather.modules.cameraweather.domain.repository

import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity

interface WeatherRepository {

   suspend fun getCurrentWeather():WeatherEntity
}