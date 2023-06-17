package com.yasser.photoweather.core.filemanager.data.repository

import android.graphics.Bitmap
import com.yasser.photoweather.core.filemanager.data.source.FileManagerDS
import com.yasser.photoweather.core.filemanager.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class FileRepositoryImp @Inject constructor(
    private val fileMangerDS: FileManagerDS,
) : FileRepository {

    override suspend fun createFile(
        fileName: String,
        deleteDirIfExist: Boolean,
        dirName: String?,
        deleteFileIfExist: Boolean,
        isExternal: Boolean
    ): File {
        return fileMangerDS.createNewFile(
            fileName.replace("/", "-"),
            deleteDirIfExist, dirName, deleteFileIfExist, isExternal
        )
    }

    override suspend fun saveBitmapToFile(bitmap: Bitmap, file: File) {
        return fileMangerDS.saveBitmapToFile(bitmap,file)
    }

}