package com.example.mpkAndroid.di

import com.example.mpkAndroid.domain.MapPositionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(
    ActivityComponent::class)
object DomainModule {

    @Provides
    fun provideMapPositionsUseCase(): MapPositionsUseCase{
        return MapPositionsUseCase()
    }
}