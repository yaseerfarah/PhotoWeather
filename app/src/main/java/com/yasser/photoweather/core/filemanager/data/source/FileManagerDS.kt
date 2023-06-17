package com.yasser.photoweather.core.filemanager.data.source

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.*
import javax.inject.Inject


class FileManagerDS @Inject constructor(@ApplicationContext  val context: Context) {

     fun createNewFile(
        fileName: String,
        deleteDirIfExist: Boolean = false,
        dirName: String? = null,
        deleteFileIfExist: Boolean,
        isExternal: Boolean
    ): File {

        val dir = if (isExternal)
            File(
                context.getExternalFilesDir(null),
                dirName ?: ""
            )
        else
            File(
                context.filesDir,
                dirName?.let { MAIN_FOLDER + File.separator + dirName } ?: MAIN_FOLDER
            )
        if (dir.exists() && deleteDirIfExist)
            dir.deleteRecursively()
        dir.mkdirs()

        val file = File(dir, fileName)
        if (deleteFileIfExist) {
            file.deleteRecursively()
        }

        return file
    }



    fun saveBitmapToFile(
        bitmap: Bitmap,
        file: File
    ){
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
        out.close()
    }


    companion object {
        const val APP_FOLDER="PhotoWeather"
        private var MAIN_FOLDER = "/$APP_FOLDER/"
    }
}