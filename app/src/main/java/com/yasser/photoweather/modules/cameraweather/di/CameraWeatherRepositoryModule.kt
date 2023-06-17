package com.yasser.photoweather.modules.cameraweather.di


import com.yasser.photoweather.modules.cameraweather.data.repository.ImageRepositoryImp
import com.yasser.photoweather.modules.cameraweather.data.repository.WeatherRepositoryImp
import com.yasser.photoweather.modules.cameraweather.domain.repository.ImageRepository
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CameraWeatherRepositoryModule {
   @Binds
   abstract fun getWeatherRepository(imp: WeatherRepositoryImp): WeatherRepository

   @Binds
   abstract fun getImageRepository(imp: ImageRepositoryImp): ImageRepository

}