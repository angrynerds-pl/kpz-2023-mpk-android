package com.example.mpkAndroid.di

import com.example.mpkAndroid.data.repository.VehicleRepositoryImpl
import com.example.mpkAndroid.domain.MapPositionsUseCase
import com.example.mpkAndroid.domain.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(
    ActivityComponent::class)
object DomainModule {

    @Provides
    fun provideMapPositionsUseCase(
        vehicleRepository: VehicleRepository
    ): MapPositionsUseCase{
        return MapPositionsUseCase(vehicleRepository)
    }

}