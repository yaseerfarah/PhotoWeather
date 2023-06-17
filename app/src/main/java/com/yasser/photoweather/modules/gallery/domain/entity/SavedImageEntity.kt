package com.yasser.photoweather.modules.gallery.domain.entity


import com.yasser.photoweather.modules.cameraweather.data.model.Condition

data class SavedImageEntity(
   val id: Long = 0,
    val fileName: String,
    val createdAt: Long,
    val localPath: String
)
