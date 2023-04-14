package com.example.mpkAndroid.data.repository

import com.example.mpkAndroid.data.network.vehiclesPositionsApiService.VehiclesPositionsApiService
import com.example.mpkAndroid.domain.VehicleRepository
import com.example.mpkAndroid.domain.model.Vehicle
import com.example.mpkAndroid.domain.model.VehicleStaticFactory
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class VehicleRepositoryImpl @Inject constructor(
    private val vehiclesPositionsApiService: VehiclesPositionsApiService,
) : VehicleRepository {
    override suspend fun getPositions(
        chosenTramLines: Collection<String>,
        chosenBusLines: Collection<String>
    ): Collection<Vehicle> {
        val retrievedVehiclesPositions =
            vehiclesPositionsApiService.getVehiclesPositions(chosenTramLines, chosenBusLines)
        val vehiclesPositions: MutableList<Vehicle> = emptyList<Vehicle>().toMutableList()
        retrievedVehiclesPositions.forEach {
            vehiclesPositions.add(VehicleStaticFactory.create(it))
        }

        return vehiclesPositions
    }
}