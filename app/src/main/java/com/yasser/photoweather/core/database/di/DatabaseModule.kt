package com.yasser.photoweather.core.database.di

import android.content.Context
import androidx.room.Room
import com.yasser.photoweather.core.database.ImageDatabase
import com.yasser.photoweather.core.database.ImageDatabase_Name
import com.yasser.photoweather.core.database.SavedImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    internal fun provideRoomDB(@ApplicationContext applicationContext: Context): ImageDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ImageDatabase::class.java, ImageDatabase_Name
        ).fallbackToDestructiveMigration()
            .build()

    }



    @Provides
    @Singleton
    internal fun provideSavedImageDao(imageDatabase: ImageDatabase): SavedImageDao {
        return imageDatabase.savedImageDao()

    }




}

