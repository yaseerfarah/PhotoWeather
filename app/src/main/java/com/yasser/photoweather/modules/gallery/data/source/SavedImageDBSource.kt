package com.yasser.photoweather.modules.gallery.data.source



import androidx.viewbinding.BuildConfig
import com.yasser.photoweather.core.database.SavedImageDao
import com.yasser.photoweather.core.database.SavedImageDto
import com.yasser.photoweather.core.network.ApiEndpoints
import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import javax.inject.Inject

class SavedImageDBSource @Inject constructor(
    private val imageDao: SavedImageDao
) {

    suspend fun getAllSavedImages():List<SavedImageDto>{
        return imageDao.selectAllSavedImages()
    }




}