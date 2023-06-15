package com.example.mpkAndroid.di

import com.example.mpkAndroid.data.repository.ReportRepositoryImpl
import com.example.mpkAndroid.data.repository.VehicleRepositoryImpl
import com.example.mpkAndroid.domain.ReportRepository
import com.example.mpkAndroid.domain.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(
    ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository

    @Binds
    abstract fun bindReportRepository(
        reportRepositoryImpl: ReportRepositoryImpl
    ): ReportRepository
}