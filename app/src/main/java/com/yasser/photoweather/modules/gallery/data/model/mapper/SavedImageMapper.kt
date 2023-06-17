package com.yasser.photoweather.modules.gallery.data.model.mapper

import com.yasser.photoweather.core.database.SavedImageDto
import com.yasser.photoweather.modules.gallery.domain.entity.SavedImageEntity

fun SavedImageDto.toSavedImageEntity():SavedImageEntity{
    return SavedImageEntity(
        id=id,
        fileName=fileName,
        createdAt=createdAt,
        localPath=localPath
    )
}