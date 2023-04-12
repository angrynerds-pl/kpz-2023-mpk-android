package com.example.mpkAndroid.data.repository

import com.example.mpkAndroid.data.network.vehiclesPositionsApiService.VehiclesPositionsApiService
import com.example.mpkAndroid.domain.VehicleRepository
import com.example.mpkAndroid.domain.model.Vehicle
import com.example.mpkAndroid.domain.model.VehicleStaticFactory
import com.example.mpkAndroid.domain.model.VehicleType
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

object VehiclesData {
    val vehicles: Collection<Vehicle> = listOf(
        Vehicle("145", VehicleType.BUS, 51.10113, 17.04063),
        Vehicle("145", VehicleType.BUS, 51.04912, 17.059267),
        Vehicle("136", VehicleType.BUS, 51.120274, 16.973097),
        Vehicle("8", VehicleType.TRAM, 51.11246, 17.039436),
        Vehicle("8", VehicleType.TRAM, 51.084827, 17.048532),
        Vehicle("8", VehicleType.TRAM, 51.12361, 17.044651),
        Vehicle("136", VehicleType.BUS, 51.123013, 16.975193),
        Vehicle("136", VehicleType.BUS, 51.13742, 16.968819),
        Vehicle("8", VehicleType.TRAM, 51.092945, 17.038195),
        Vehicle("16", VehicleType.TRAM, 51.10069, 17.036085),
        Vehicle("16", VehicleType.TRAM, 51.10544, 17.037998)

    )
}

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