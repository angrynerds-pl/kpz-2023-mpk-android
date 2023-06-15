package com.example.mpkAndroid.di

import com.example.mpkAndroid.domain.MapPositionsUseCase
import com.example.mpkAndroid.domain.ReportRepository
import com.example.mpkAndroid.domain.ReportsUseCase
import com.example.mpkAndroid.domain.VehicleRepository
import com.example.mpkAndroid.domain.VehiclesFilterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(
    ActivityComponent::class
)
object DomainModule {

    @Provides
    fun provideMapPositionsUseCase(
        vehicleRepository: VehicleRepository
    ): MapPositionsUseCase {
        return MapPositionsUseCase(vehicleRepository)
    }

    @Provides
    fun provideVehiclesFilterUseCase(
    ): VehiclesFilterUseCase {
        return VehiclesFilterUseCase()
    }

    @Provides
    fun provideReportsUseCase(
        reportRepository: ReportRepository
    ): ReportsUseCase {
        return ReportsUseCase(reportRepository)
    }


}