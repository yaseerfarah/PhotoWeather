package com.yasser.photoweather.core.filemanager.di


import com.yasser.photoweather.core.filemanager.data.repository.FileRepositoryImp
import com.yasser.photoweather.core.filemanager.domain.repository.FileRepository
import com.yasser.photoweather.modules.cameraweather.data.repository.WeatherRepositoryImp
import com.yasser.photoweather.modules.cameraweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FileRepositoryModule {
   @Binds
   abstract fun getFileRepository(imp: FileRepositoryImp): FileRepository

}