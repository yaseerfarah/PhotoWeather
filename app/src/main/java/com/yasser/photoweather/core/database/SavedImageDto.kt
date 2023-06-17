package com.yasser.photoweather.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SavedImage_TableName)
data class SavedImageDto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fileName: String,
    val createdAt: Long,
    val localPath: String
)

const val SavedImage_TableName="SavedImage"
