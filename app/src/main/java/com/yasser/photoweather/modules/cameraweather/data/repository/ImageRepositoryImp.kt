package com.yasser.photoweather.modules.cameraweather.data.repository




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
import javax.inject.Inject

class ImageRepositoryImp@Inject constructor(
    private val source: ImageDBSource,
    private val fileRepository: FileRepository
): ImageRepository {
    override suspend fun saveImageTooDatabase(bitmap: Bitmap):String {
        val fileName=System.currentTimeMillis().toString()+".jpeg"
        val imageFile=fileRepository.createFile(
            fileName = fileName,
            deleteDirIfExist = false,
            dirName = APP_FOLDER,
            deleteFileIfExist = false,
            isExternal = true
        )

        fileRepository.saveBitmapToFile(bitmap,imageFile)
        source.saveImageFileToDatabase(fileName,imageFile.absolutePath)
        return imageFile.absolutePath
    }


}