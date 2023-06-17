package com.yasser.photoweather.modules.main.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.yasser.photoweather.modules.main.presentation.navigation.MainNavigationEvent
import com.yasser.photoweather.modules.main.presentation.uimodel.MainScreensEnum
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiModel
import com.yasser.photoweather.modules.main.presentation.viewmodel.MainViewModel
import com.yasser.photoweather.R
import com.yasser.photoweather.base.presentation.navigation.NavigationCoordinator
import com.yasser.photoweather.base.presentation.view.BaseActivity
import com.yasser.photoweather.databinding.ActivityMainBinding
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUIEvents
import com.yasser.photoweather.modules.main.presentation.uimodel.MainUiState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainUiModel, MainUiState,MainUIEvents, MainViewModel>(),
    NavController.OnDestinationChangedListener {
    override val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var navigation: NavigationCoordinator<MainNavigationEvent>

    private var isFirstScreen=true


    private  val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController
    }

    override fun bindView(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(this)
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(this)
    }

    override fun handleOrientation() {

    }

    override fun initViews() {
        navigation.init(navController)
        onBackPress()
    }

    override fun initListener() {

    }

    override fun render(ui: MainUiModel) {
        isFirstScreen=ui.currentScreen==MainScreensEnum.GALLERY
    }

    private fun onBackPress() {
        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!isFirstScreen)
                        navigation.onEvent(MainNavigationEvent.BackToGalleryScreen)
                    else
                        finish()
                }
            })


    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id){
            R.id.galleryFragment->viewModel.sendEvent(MainUIEvents.NavigateToScreen(screen = MainScreensEnum.GALLERY))
            R.id.previewFragment->viewModel.sendEvent(MainUIEvents.NavigateToScreen(screen = MainScreensEnum.PREVIEW))
            R.id.cameraWeatherFragment->viewModel.sendEvent(MainUIEvents.NavigateToScreen(screen = MainScreensEnum.CAMERA_WEATHER))

        }
    }

    companion object{

        fun startInstance(context: Context?) {
            context?.startActivity(
                Intent(context, MainActivity::class.java).apply {
                }
            )
            (context as Activity).finishAffinity()

        }

    }



}


