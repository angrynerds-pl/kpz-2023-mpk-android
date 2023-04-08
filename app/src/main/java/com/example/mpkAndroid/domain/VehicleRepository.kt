package com.example.mpkAndroid.domain

import com.example.mpkAndroid.domain.model.Vehicle

interface VehicleRepository {

    /**
     * @param chosenLines should be collection with unique values
     */
    fun getPositions(chosenLines : Collection<String>) : Collection<Vehicle>
}