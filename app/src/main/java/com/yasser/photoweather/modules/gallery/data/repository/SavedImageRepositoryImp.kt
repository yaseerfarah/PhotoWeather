package com.yasser.photoweather.modules.gallery.data.repository




import android.graphics.Bitmap
import com.yasser.photoweather.core.filemanager.data.source.FileManagerDS.Companion.APP_FOLDER
import com.yasser.photoweather.core.filemanager.domain.repository.FileRepository
import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import com.yasser.photoweather.modules.cameraweather.data.model.mapper.toWeatherEntity
import com.yasser.photoweather.modules.cameraweather.data.source.ImageDBSource
import com.yasser.photoweather.modules.cameraweather.data.source.WeatherApiSource
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import com.yasser.photoweather.modules.cameraweather.domain.repository.ImageRepository
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import com.yasser.photoweather.modules.gallery.data.model.mapper.toSavedImageEntity
import com.yasser.photoweather.modules.gallery.data.source.SavedImageDBSource
import com.yasser.photoweather.modules.gallery.domain.entity.SavedImageEntity
import com.yasser.photoweather.modules.gallery.domain.repository.SavedImageRepository
import javax.inject.Inject

class SavedImageRepositoryImp@Inject constructor(
    private val source: SavedImageDBSource,
): SavedImageRepository {
    override suspend fun getAllSavedImages(): List<SavedImageEntity> {
        return source.getAllSavedImages().map {
            it.toSavedImageEntity()
        }
    }


}