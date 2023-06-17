package com.yasser.photoweather.modules.main.presentation.navigation

import android.content.Context
import androidx.navigation.NavController
import com.yasser.photoweather.modules.main.presentation.view.MainActivity
import com.yasser.photoweather.base.presentation.navigation.NavigationCoordinator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
 open class MainNavigationCoordinator @Inject constructor(
    private val mainFlowNavigation: MainFlowNavigation
)
    : NavigationCoordinator<MainNavigationEvent>() {

     override fun init(param: Any) {
         mainFlowNavigation.setNavController(param as NavController)
     }

     override fun onStart(context: Context?, param: Any?) {
         MainActivity.startInstance(context)
     }

     override fun onEvent(event: MainNavigationEvent) {
         when(event){
             is MainNavigationEvent.BackToGalleryScreen->mainFlowNavigation.backToHomeScreen()
             is MainNavigationEvent.NavigateToPreviewScreen->mainFlowNavigation.navigateToPreviewScreen(event.localPath)
             is MainNavigationEvent.NavigateToCameraWeatherScreen->mainFlowNavigation.navigateToCameraWeatherScreen()
         }
     }


 }