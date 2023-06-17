package com.yasser.photoweather.core.filemanager.domain.repository


import android.graphics.Bitmap
import java.io.File

interface FileRepository {
   suspend fun createFile(
        fileName: String,
        deleteDirIfExist: Boolean,
        dirName: String? = null,
        deleteFileIfExist: Boolean = false,
        isExternal: Boolean = false
    ): File


   suspend fun saveBitmapToFile(
       bitmap: Bitmap,
       file: File
   )
}