package com.yasser.photoweather.modules.gallery.presentation.uimodel

import com.yasser.photoweather.R
import com.yasser.photoweather.core.extensions.convertToDate
import com.yasser.photoweather.core.extensions.convertToUiDate
import com.yasser.photoweather.modules.gallery.domain.entity.SavedImageEntity
import java.net.ConnectException
import java.net.UnknownHostException




fun List<SavedImageEntity>.toGalleryUiState():GalleryUiState{
    return GalleryUiState(
        isLoading = false,
        data = this,
        errorMsg = null
    )
}

fun List<SavedImageEntity>.toListOfSavedImageUiModel():List<SavedImageUiModel>{
    return this.map { savedIamgeEntity->
        SavedImageUiModel(
            id=savedIamgeEntity.id,
            localPath = savedIamgeEntity.localPath,
            createdAt = savedIamgeEntity.createdAt.convertToDate()?.convertToUiDate()?:""
        )
    }
}



fun Throwable.toGalleryUiState():GalleryUiState{
    return GalleryUiState(
        data = listOf(),
        isLoading = false,
        errorMsg = R.string.msgUnexpextedError,
    )
}
