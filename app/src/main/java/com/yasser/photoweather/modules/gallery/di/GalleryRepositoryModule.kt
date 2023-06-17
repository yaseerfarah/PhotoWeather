package com.yasser.photoweather.modules.cameraweather.di


import com.yasser.photoweather.modules.cameraweather.data.repository.ImageRepositoryImp
import com.yasser.photoweather.modules.cameraweather.data.repository.WeatherRepositoryImp
import com.yasser.photoweather.modules.cameraweather.domain.repository.ImageRepository
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import com.yasser.photoweather.modules.gallery.data.repository.SavedImageRepositoryImp
import com.yasser.photoweather.modules.gallery.domain.repository.SavedImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GalleryRepositoryModule {

   @Binds
   abstract fun getSavedImageRepository(imp: SavedImageRepositoryImp): SavedImageRepository

}