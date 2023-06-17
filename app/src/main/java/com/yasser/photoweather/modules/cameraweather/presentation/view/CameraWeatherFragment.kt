package com.yasser.photoweather.modules.cameraweather.presentation.view

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Surface.*
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasser.photoweather.R
import com.yasser.photoweather.base.presentation.navigation.NavigationCoordinator
import com.yasser.photoweather.base.presentation.view.BaseFragment
import com.yasser.photoweather.base.presentation.view.customview.showLoading
import com.yasser.photoweather.core.extensions.*
import com.yasser.photoweather.core.extensions.onClick
import com.yasser.photoweather.core.extensions.visible
import com.yasser.photoweather.databinding.FragmentCameraWeatherBinding
import com.yasser.photoweather.databinding.FragmentGalleryBinding
import com.yasser.photoweather.modules.cameraweather.presentation.uimodel.CameraWeatherUIEvents
import com.yasser.photoweather.modules.cameraweather.presentation.uimodel.CameraWeatherUiModel
import com.yasser.photoweather.modules.cameraweather.presentation.uimodel.CameraWeatherUiState
import com.yasser.photoweather.modules.cameraweather.presentation.viewmodel.CameraWeatherViewModel
import com.yasser.photoweather.modules.gallery.presentation.adapter.ImageListAdapter
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUIEvents
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUiModel
import com.yasser.photoweather.modules.gallery.presentation.uimodel.GalleryUiState
import com.yasser.photoweather.modules.gallery.presentation.viewmodel.GalleryViewModel
import com.yasser.photoweather.modules.main.presentation.navigation.MainNavigationEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class  CameraWeatherFragment :  BaseFragment<FragmentCameraWeatherBinding, CameraWeatherUiModel, CameraWeatherUiState, CameraWeatherUIEvents, CameraWeatherViewModel>(){



    override val viewModel by viewModels<CameraWeatherViewModel> ()

    @Inject
    lateinit var navigation: NavigationCoordinator<MainNavigationEvent>

    private var registerForActivityResultPermission: ActivityResultLauncher<String>? =null

    private var imageCapture:ImageCapture?=null

    override fun bindView(): FragmentCameraWeatherBinding {
        return FragmentCameraWeatherBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        requestPermission()

    }

    private fun requestPermission() {
        viewModel.sendEvent(CameraWeatherUIEvents.ResetInitialState)
        requestCameraPermissionIfMessing{granted->
            viewModel.sendEvent(CameraWeatherUIEvents.RequestCameraPermission(granted))
            if (granted)
                startCamera()
        }
    }



    private fun requestCameraPermissionIfMessing(onResult: (Boolean) -> Unit) {
        if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            onResult(true)
        }else{
            if (registerForActivityResultPermission==null) {
                registerForActivityResultPermission =
                    registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                        onResult(it)
                    }
            }
            registerForActivityResultPermission?.launch(Manifest.permission.CAMERA)
        }
    }


    private fun startCamera() {
        val processCameraProvider=ProcessCameraProvider.getInstance(requireContext())
        processCameraProvider.addListener(
            {
            try {
                val cameraProvider=processCameraProvider.get()
                val previewUseCase=buildCameraPreviewUseCase()
                imageCapture=buildImageCaptureUseCase()
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(viewLifecycleOwner,CameraSelector.DEFAULT_BACK_CAMERA,previewUseCase,imageCapture)
            }catch (e:Exception){
                viewModel.sendEvent(CameraWeatherUIEvents.CameraInitializationError)
            }
            },ContextCompat.getMainExecutor(requireContext()))
    }

    private fun buildCameraPreviewUseCase():Preview{
        return Preview.Builder().build().apply {
            setSurfaceProvider(binding.cameraPreview.surfaceProvider)
        }
    }

    private fun buildImageCaptureUseCase():ImageCapture{
        return ImageCapture.Builder()
            .build()
    }


    private fun captureImage(){

        binding.stateView.showLoading(true, backgroundColor = R.color.white, blockTouchListener = true)
        binding.captureBtn.visible(false)
        imageCapture?.takePicture(ContextCompat.getMainExecutor(requireContext()), object : OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                binding.previewImage.visible(true)
                binding.previewImage.setImageBitmap(image.image!!.toImageBitmap().rotate(image.imageInfo.rotationDegrees.toFloat()))
                Handler(Looper.getMainLooper()).postDelayed({
                    val bitmap=takeScreenshot(binding.cameraLayout,binding.cameraLayout.height,binding.cameraLayout.width)
                    binding.previewImage.setImageBitmap(bitmap)
                    viewModel.sendEvent(CameraWeatherUIEvents.SaveImageBitmap(bitmap))
                },500)

            }

            override fun onError(exception: ImageCaptureException) {
                binding.stateView.showLoading(false)
                binding.captureBtn.visible(true)
            }

        })

    }

    fun takeScreenshot(view: View, height: Int, width: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return bitmap
    }

    override fun initListener() {
        binding.backBtn.onClick {
            navigation.onEvent(MainNavigationEvent.BackToGalleryScreen)
        }
        binding.captureBtn.onClick {
            captureImage()
        }
    }

    override fun render(ui: CameraWeatherUiModel) {

        if (ui.isImageSaved){
            navigation.onEvent(MainNavigationEvent.NavigateToPreviewScreen(localPath = ui.imageLocalPath!!))
        }

        setWeatherContent(ui)
        binding.stateView.showLoading(ui.showLoading, backgroundColor = R.color.white, blockTouchListener = ui.showLoading)
        when{
            ui.showUnexpectedError->{
                binding.stateView.setError(
                    message = ui.errorMsg!!, retryAction = {
                        requestPermission()
                    }, retryTextColor = R.color.white, retryBackground = R.drawable.ic_retry_btn_bg, backgroundColor = R.color.white, retryIcon = R.drawable.ic_retry
                )
            }
            ui.showNetworkError->{
                binding.stateView.setNetworkError(message = resources.getString(ui.errorMsg!!), retryAction = {
                    requestPermission()
                }, retryTextColor = R.color.white,backgroundColor = R.color.white, retryBackground = R.drawable.ic_retry_btn_bg, retryIcon = R.drawable.ic_retry)
            }
        }
    }


    private fun setWeatherContent(ui: CameraWeatherUiModel){
        binding.cameraPreview.visible(!ui.showLoading)
        binding.captureBtn.visible(!ui.showLoading&&ui.errorMsg==null)
        binding.weatherContent.location.text=ui.location
        binding.weatherContent.tempC.text=ui.temp_c.toString()
        binding.weatherContent.condition.text=ui.condition
        binding.weatherContent.conditionIcon.loadImage(ui.iconUrl,null,null)


    }


}