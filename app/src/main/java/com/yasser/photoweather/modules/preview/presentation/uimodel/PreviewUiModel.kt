package com.yasser.photoweather.modules.preview.presentation.uimodel

import androidx.annotation.StringRes

data class PreviewUiModel(
    val imageUrl: String,
    val isLoading:Boolean,
    @StringRes val errorMsg:Int?,
    val showUnexpectedError:Boolean,)
