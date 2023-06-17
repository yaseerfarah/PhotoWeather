package com.yasser.photoweather.modules.gallery.domain.interactors


import com.yasser.photoweather.base.domain.interactors.SuspendUseCase
import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import com.yasser.photoweather.modules.gallery.domain.entity.SavedImageEntity
import com.yasser.photoweather.modules.gallery.domain.repository.SavedImageRepository
import javax.inject.Inject

class GetAllSavedImagesUseCase @Inject constructor(
    private val repository: SavedImageRepository,
): SuspendUseCase<Unit, List<SavedImageEntity>>() {
    override suspend fun invoke(params: Unit):  List<SavedImageEntity> {
      return repository.getAllSavedImages()
    }
}