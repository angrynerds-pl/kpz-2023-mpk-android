package com.example.mpkAndroid.domain

import com.example.mpkAndroid.domain.model.Vehicle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

private const val STARTING_LATITUDE = 51.11
private const val STARTING_LONGITUDE = 17.04
private const val ZOOM_LVL = 13f


class MapPositionsUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository
) {

    suspend fun getVehiclesPositions(
        chosenTramLines: Collection<String>,
        chosenBusLines: Collection<String>
    ): Collection<Vehicle> {
        if(chosenTramLines.isEmpty() && chosenBusLines.isEmpty())
            return emptyList()

        return vehicleRepository.getPositions(chosenTramLines, chosenBusLines)
    }

    fun getCameraStartingPosition(): CameraPosition {
        val wroclaw = LatLng(STARTING_LATITUDE, STARTING_LONGITUDE)
        return CameraPosition.fromLatLngZoom(wroclaw, ZOOM_LVL)
    }
}