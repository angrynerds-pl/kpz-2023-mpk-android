package com.example.mpkAndroid.domain

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

private const val STARTING_LATITUDE = 51.11
private const val STARTING_LONGITUDE = 17.04
private const val ZOOM_LVL = 13f


class MapPositionsUseCase @Inject constructor(

){

    fun getStartingPosition() : CameraPosition {
        val wroclaw = LatLng(STARTING_LATITUDE, STARTING_LONGITUDE)
        return CameraPosition.fromLatLngZoom(wroclaw, ZOOM_LVL)
    }
}