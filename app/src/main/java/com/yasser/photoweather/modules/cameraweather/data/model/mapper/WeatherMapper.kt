package com.yasser.photoweather.modules.cameraweather.data.model.mapper

import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity


fun WeatherResponse.toWeatherEntity():WeatherEntity{
    return WeatherEntity(
        location=location.name,
         country=location.country,
         region=location.region,
         condition=current.condition.text,
         iconUrl=current.condition.icon,
         temp_c=current.temp_c,
         temp_f=current.temp_f,
         is_morning=current.is_day==1,
    )
}