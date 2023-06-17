package com.yasser.photoweather.modules.gallery.presentation.uimodel

import androidx.annotation.StringRes
import com.yasser.photoweather.modules.gallery.domain.entity.SavedImageEntity

data class GalleryUiState(val data:List<SavedImageEntity> = listOf(), val isLoading:Boolean, @StringRes val errorMsg:Int?)
