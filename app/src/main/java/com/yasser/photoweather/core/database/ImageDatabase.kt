package com.yasser.photoweather.core.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [SavedImageDto::class],
    version = 1,
    exportSchema = false
)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun savedImageDao(): SavedImageDao

}

const val ImageDatabase_Name = "images-db"



