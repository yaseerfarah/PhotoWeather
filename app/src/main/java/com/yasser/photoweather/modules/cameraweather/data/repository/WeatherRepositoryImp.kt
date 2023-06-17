package com.yasser.photoweather.modules.cameraweather.data.repository




import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import com.yasser.photoweather.modules.cameraweather.data.model.mapper.toWeatherEntity
import com.yasser.photoweather.modules.cameraweather.data.source.WeatherApiSource
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImp@Inject constructor(
    private val source: WeatherApiSource
): WeatherRepository {
    override suspend  fun getCurrentWeather(): WeatherEntity {
        return source.getCurrentWeather().toWeatherEntity()
    }


}