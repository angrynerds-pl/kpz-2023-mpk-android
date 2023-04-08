package com.example.mpkAndroid.domain

import com.example.mpkAndroid.domain.model.Vehicle

interface VehicleRepository {
    fun getPositions(chosenLines : Set<String>) : List<Vehicle>
}