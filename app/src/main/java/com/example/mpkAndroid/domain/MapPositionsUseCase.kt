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
){

    fun getVehiclesPositions(chosenLines: Set<String>) : List<Vehicle>{
        return vehicleRepository.getPositions(chosenLines)
    }

    fun getCameraStartingPosition() : CameraPosition {
        val wroclaw = LatLng(STARTING_LATITUDE, STARTING_LONGITUDE)
        return CameraPosition.fromLatLngZoom(wroclaw, ZOOM_LVL)
    }
}