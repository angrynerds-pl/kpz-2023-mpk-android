package com.example.mpkAndroid.domain

import com.example.mpkAndroid.domain.model.Vehicle

interface VehicleRepository {

    /**
     * @param chosenTramLines should be collection with unique values
     * @param chosenBusLines should be collection with unique values
     */
    suspend fun getPositions(
        chosenTramLines: Collection<String>,
        chosenBusLines: Collection<String>
    ): Collection<Vehicle>
}