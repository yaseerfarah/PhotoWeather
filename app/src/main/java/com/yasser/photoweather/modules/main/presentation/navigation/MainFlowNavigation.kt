package com.yasser.photoweather.modules.main.presentation.navigation


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.yasser.photoweather.R
import com.yasser.photoweather.modules.preview.presentation.view.PreviewFragment.Companion.PREVIEW_PARAM
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainFlowNavigation @Inject constructor(){

    private lateinit var navController:NavController

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun backToHomeScreen(){
        navController.popBackStack()
    }

    fun navigateToPreviewScreen(localPath:String){
        val argument=Bundle()
        argument.putString(PREVIEW_PARAM,localPath)
        val id=if (navController.currentDestination?.id==R.id.galleryFragment) R.id.action_galleryFragment_to_previewFragment else R.id.action_cameraWeatherFragment_to_previewFragment
        navController.navigate(id,argument)
    }


    fun navigateToCameraWeatherScreen(){
        navController.navigate(R.id.action_galleryFragment_to_cameraWeatherFragment)
    }






}