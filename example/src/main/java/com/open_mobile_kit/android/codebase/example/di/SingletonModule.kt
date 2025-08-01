package com.open_mobile_kit.android.codebase.example.di

import com.open_mobile_kit.android.codebase.presentation.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {
    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager = NavigationManager()
}
