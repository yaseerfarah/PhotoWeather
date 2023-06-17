package com.yasser.photoweather.modules.preview.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasser.photoweather.R
import com.yasser.photoweather.base.presentation.navigation.NavigationCoordinator
import com.yasser.photoweather.base.presentation.view.BaseFragment
import com.yasser.photoweather.base.presentation.view.customview.showLoading
import com.yasser.photoweather.core.extensions.loadImage
import com.yasser.photoweather.core.extensions.onClick
import com.yasser.photoweather.databinding.FragmentGalleryBinding
import com.yasser.photoweather.databinding.FragmentPreviewBinding
import com.yasser.photoweather.modules.gallery.presentation.adapter.ImageListAdapter
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUIEvents
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUiModel
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUiState
import com.yasser.photoweather.modules.gallery.presentation.viewmodel.GalleryViewModel
import com.yasser.photoweather.modules.main.presentation.navigation.MainNavigationEvent
import com.yasser.photoweather.modules.preview.presentation.uimodel.PreviewUIEvents
import com.yasser.photoweather.modules.preview.presentation.uimodel.PreviewUiModel
import com.yasser.photoweather.modules.preview.presentation.uimodel.PreviewUiState
import com.yasser.photoweather.modules.preview.presentation.viewmodel.PreviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class PreviewFragment : BaseFragment<FragmentPreviewBinding, PreviewUiModel, PreviewUiState, PreviewUIEvents, PreviewViewModel>(){



    override val viewModel by viewModels<PreviewViewModel> ()

    @Inject
    lateinit var navigation: NavigationCoordinator<MainNavigationEvent>

    private var imageUri:Uri?=null

    override fun bindView(): FragmentPreviewBinding {
        return FragmentPreviewBinding.inflate(layoutInflater)
    }

    override fun initViews() {

        viewModel.sendEvent(PreviewUIEvents.StartShowImage(arguments?.getString(PREVIEW_PARAM)))
    }

    override fun initListener() {
        binding.backBtn.onClick {
            navigation.onEvent(MainNavigationEvent.BackToGalleryScreen)
        }
        binding.shareBtn.onClick {
            shareImage(imageUri)
        }
    }

    override fun render(ui: PreviewUiModel) {

        if (ui.imageUrl.isNotEmpty()) {
            binding.previewImage.loadImage(ui.imageUrl, null, null)
            imageUri= FileProvider.getUriForFile(requireContext(), requireContext().packageName+".fileprovider", File(ui.imageUrl))
        }

        binding.stateView.showLoading(ui.isLoading)
        when{
            ui.showUnexpectedError->{
                binding.stateView.setError(
                    message = ui.errorMsg!!, retryAction = {
                        viewModel.sendEvent(PreviewUIEvents.StartShowImage(arguments?.getString(PREVIEW_PARAM)))
                    }, retryTextColor = R.color.white, retryBackground = R.drawable.ic_retry_btn_bg, backgroundColor = R.color.white, retryIcon = R.drawable.ic_retry
                )
            }
        }
    }

    fun shareImage(bitmapUri: Uri?) {
        bitmapUri.let {
            // Create a sharing intent
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_SEND
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                putExtra(Intent.EXTRA_STREAM,bitmapUri)
            }
            startActivity(Intent.createChooser(intent,""))
        }
    }

    companion object{
        const val PREVIEW_PARAM="preview_param"
    }


}