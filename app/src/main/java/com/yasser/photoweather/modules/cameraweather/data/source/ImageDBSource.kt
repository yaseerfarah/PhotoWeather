package com.yasser.photoweather.modules.cameraweather.data.source



import androidx.viewbinding.BuildConfig
import com.yasser.photoweather.core.database.SavedImageDao
import com.yasser.photoweather.core.database.SavedImageDto
import com.yasser.photoweather.core.network.ApiEndpoints
import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import javax.inject.Inject

class ImageDBSource @Inject constructor(
    private val imageDao: SavedImageDao
) {

    suspend fun saveImageFileToDatabase(fileName:String,filePath:String){
         imageDao.insertFile(
            SavedImageDto(
                fileName = fileName,
                localPath = filePath,
                createdAt = System.currentTimeMillis()
            )
        )
    }




}