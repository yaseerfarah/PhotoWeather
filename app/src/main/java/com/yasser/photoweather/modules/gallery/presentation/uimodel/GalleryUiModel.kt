package com.yasser.photoweather.modules.gallery.presentation.uimodel

import androidx.annotation.StringRes


data class GalleryUiModel(
    val data:List<SavedImageUiModel>,
    val showLoading:Boolean,
    @StringRes val errorMsg:Int?,
    val showEmptyState:Boolean,
    val showUnexpectedError:Boolean,
)
