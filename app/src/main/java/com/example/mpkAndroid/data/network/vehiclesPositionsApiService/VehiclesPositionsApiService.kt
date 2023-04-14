package com.example.mpkAndroid.data.network.vehiclesPositionsApiService

import com.example.mpkAndroid.data.model.VehicleEntity
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

@ViewModelScoped
interface VehiclesPositionsApiService {

    @FormUrlEncoded
    @POST("bus_position")
    suspend fun getVehiclesPositions(
        @Field("busList[tram][]") trams: Collection<String>,
        @Field("busList[bus][]") bus: Collection<String>
    ): Collection<VehicleEntity>
}