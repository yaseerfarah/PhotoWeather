package com.yasser.photoweather.modules.gallery.presentation.uimodel

import androidx.recyclerview.widget.DiffUtil


data class SavedImageUiModel(
    val id:Long,
    val localPath:String,
    val createdAt:String

){
    fun areContentSame(oldModel:SavedImageUiModel):Boolean{
        return id == oldModel.id
                && localPath == oldModel.localPath
                && createdAt == oldModel.createdAt

    }
}


object SavedImageDiffUtils: DiffUtil.ItemCallback<SavedImageUiModel>(){
    override fun areItemsTheSame(oldItem: SavedImageUiModel, newItem: SavedImageUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SavedImageUiModel, newItem: SavedImageUiModel): Boolean {
        return newItem.areContentSame(oldItem)
    }

}




