package com.yasser.photoweather.modules.main.di



import com.yasser.photoweather.modules.main.presentation.navigation.MainFlowNavigation
import com.yasser.photoweather.modules.main.presentation.navigation.MainNavigationCoordinator
import com.yasser.photoweather.modules.main.presentation.navigation.MainNavigationEvent
import com.yasser.photoweather.base.presentation.navigation.NavigationCoordinator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainNavigationModule {

    @Provides
    @Singleton
    fun provideMainNavigationCoordinator(
        mainFlowNavigation: MainFlowNavigation
    ): NavigationCoordinator<MainNavigationEvent> =
        MainNavigationCoordinator(
            mainFlowNavigation =mainFlowNavigation
        )
}
