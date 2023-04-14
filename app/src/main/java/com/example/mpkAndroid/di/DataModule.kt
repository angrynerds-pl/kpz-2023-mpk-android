package com.example.mpkAndroid.di

import com.example.mpkAndroid.data.network.vehiclesPositionsApiService.VehiclesPositionsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(
    ActivityRetainedComponent::class
)
object DataModule {

    @Provides
    fun providesVehiclesPositionsApiService(): VehiclesPositionsApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mpk.wroc.pl/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

        return retrofit.create(VehiclesPositionsApiService::class.java)
    }
}