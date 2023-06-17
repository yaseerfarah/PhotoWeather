package com.yasser.photoweather.modules.gallery.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasser.photoweather.R
import com.yasser.photoweather.base.presentation.navigation.NavigationCoordinator
import com.yasser.photoweather.base.presentation.view.BaseFragment
import com.yasser.photoweather.base.presentation.view.customview.showLoading
import com.yasser.photoweather.core.extensions.onClick
import com.yasser.photoweather.databinding.FragmentGalleryBinding
import com.yasser.photoweather.modules.gallery.presentation.adapter.ImageListAdapter
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUIEvents
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUiModel
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUiState
import com.yasser.photoweather.modules.gallery.presentation.viewmodel.GalleryViewModel
import com.yasser.photoweather.modules.main.presentation.navigation.MainNavigationEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment :  BaseFragment<FragmentGalleryBinding, GalleryUiModel, GalleryUiState,GalleryUIEvents, GalleryViewModel>(){



    override val viewModel by viewModels<GalleryViewModel> ()

    @Inject
    lateinit var navigation: NavigationCoordinator<MainNavigationEvent>

    private val imageListAdapter by lazy {
        ImageListAdapter(requireContext()){savedImageUiModel->
            navigation.onEvent(MainNavigationEvent.NavigateToPreviewScreen(savedImageUiModel.localPath))

        }
    }

    override fun bindView(): FragmentGalleryBinding {
        return FragmentGalleryBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.imageRecycler.apply {
            adapter=imageListAdapter
            layoutManager= GridLayoutManager(requireContext(), 2)
        }
        viewModel.sendEvent(GalleryUIEvents.GetAllSavedImage)
    }

    override fun initListener() {
        binding.addBtn.onClick {
            navigation.onEvent(MainNavigationEvent.NavigateToCameraWeatherScreen)
        }
    }

    override fun render(ui: GalleryUiModel) {

        imageListAdapter.submitList( ui.data)

        binding.stateView.showLoading(ui.showLoading)
        when{
            ui.showUnexpectedError->{
                binding.stateView.setError(
                    message = ui.errorMsg!!, retryAction = {
                        viewModel.sendEvent(GalleryUIEvents.GetAllSavedImage)
                    }, retryTextColor = R.color.white, retryBackground = R.drawable.ic_retry_btn_bg, backgroundColor = R.color.white, retryIcon = R.drawable.ic_retry
                )
            }
            ui.showEmptyState->{
                binding.stateView.setEmpty(emptyMsg = R.string.empty)
            }
        }
    }


}