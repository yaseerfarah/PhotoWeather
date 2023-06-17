package com.yasser.photoweather.modules.cameraweather.domain.interactors


import com.yasser.photoweather.base.domain.interactors.SuspendUseCase
import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import com.yasser.photoweather.modules.cameraweather.domain.entity.param.SaveImageBitmapParam
import com.yasser.photoweather.modules.cameraweather.domain.repository.ImageRepository
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import javax.inject.Inject

class SaveImageBitmapUseCase @Inject constructor(
    private val repository: ImageRepository,
): SuspendUseCase<SaveImageBitmapParam, String>() {
    override suspend fun invoke(params: SaveImageBitmapParam):  String {
      return repository.saveImageTooDatabase(params.bitmap)
    }
}