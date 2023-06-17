package com.yasser.photoweather.modules.cameraweather.domain.interactors


import com.yasser.photoweather.base.domain.interactors.SuspendUseCase
import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
): SuspendUseCase<Unit, WeatherEntity>() {
    override suspend fun invoke(params: Unit):  WeatherEntity {
      return repository.getCurrentWeather()
    }
}