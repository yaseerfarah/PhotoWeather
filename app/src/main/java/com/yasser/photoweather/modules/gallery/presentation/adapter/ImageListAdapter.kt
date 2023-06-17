package com.yasser.photoweather.modules.gallery.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.yasser.photoweather.core.extensions.loadImage
import com.yasser.photoweather.core.extensions.onClick
import com.yasser.photoweather.databinding.ImageItemBinding
import com.yasser.photoweather.modules.gallery.presentation.uimodel.SavedImageDiffUtils
import com.yasser.photoweather.modules.gallery.presentation.uimodel.SavedImageUiModel
import java.util.concurrent.TimeUnit


class ImageListAdapter constructor(
    private val context: Context,
    private val onClick:(SavedImageUiModel)->Unit,
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    private val mDiffer: AsyncListDiffer<SavedImageUiModel?> =
        AsyncListDiffer(this, SavedImageDiffUtils)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mDiffer.currentList[position]!!
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bind = ImageItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(bind)
    }

    inner class ViewHolder constructor(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SavedImageUiModel) {
            binding.imageCover.loadImage(item.localPath,placeholderDrawable = null, progressBar = binding.progressBar)
            binding.date.text=item.createdAt
            initAction(item)

        }
        private fun initAction(item: SavedImageUiModel){
            binding.root.onClick {
                onClick.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    fun submitList(items: List<SavedImageUiModel>) {
        mDiffer.submitList(items)
    }
}


