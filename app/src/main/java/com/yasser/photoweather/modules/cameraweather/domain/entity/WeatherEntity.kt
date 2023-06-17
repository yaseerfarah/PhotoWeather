package com.yasser.photoweather.modules.cameraweather.domain.entity

import com.yasser.photoweather.modules.cameraweather.data.model.Condition

data class WeatherEntity(
    val location:String,
    val country: String,
    val region: String,
    val condition: String,
    val iconUrl:String,
    val temp_c: Double,
    val temp_f: Double,
    val is_morning: Boolean,
)
