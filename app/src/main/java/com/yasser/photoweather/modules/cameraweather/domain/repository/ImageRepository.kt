package com.yasser.photoweather.modules.cameraweather.domain.repository

import android.graphics.Bitmap
import com.yasser.photoweather.modules.cameraweather.domain.entity.WeatherEntity

interface ImageRepository {

   suspend fun saveImageTooDatabase(bitmap: Bitmap):String
}