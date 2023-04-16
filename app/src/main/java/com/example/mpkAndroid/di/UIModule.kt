package com.example.mpkAndroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

@Module
@InstallIn(
    ActivityRetainedComponent::class
)
object UIModule {

    @Provides
    fun provideScheduledBackgroundExecutorServices(): ScheduledExecutorService {
        return Executors.newSingleThreadScheduledExecutor()
    }
}