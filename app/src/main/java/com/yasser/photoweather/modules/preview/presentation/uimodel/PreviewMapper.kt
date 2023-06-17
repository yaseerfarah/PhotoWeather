package com.yasser.photoweather.modules.preview.presentation.uimodel

import com.yasser.photoweather.R


fun String?.toPreviewUiModel(isLoading:Boolean):PreviewUiModel{
    return if (this!=null)
        PreviewUiModel(
            imageUrl=this,
            showUnexpectedError = false,
            errorMsg = null,
            isLoading = false
    )
    else
        PreviewUiModel(
            imageUrl="",
            showUnexpectedError = !isLoading,
            errorMsg = if(isLoading)null else  R.string.msgUnexpextedError,
            isLoading = isLoading
        )

}