package com.yasser.photoweather.modules.gallery.domain.repository

import android.graphics.Bitmap
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity
import com.yasser.photoweather.modules.gallery.domain.entity.SavedImageEntity

interface SavedImageRepository {

   suspend fun getAllSavedImages():List<SavedImageEntity>
}