package com.yasser.photoweather.core.application

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PhotoWeatherApplication : Application() {


    override fun onCreate() {
        super.onCreate()

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}

