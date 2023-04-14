package com.example.mpkAndroid.domain.model

import com.example.mpkAndroid.data.model.VehicleEntity

class VehicleStaticFactory {
    companion object {
        fun create(vehicleEntity: VehicleEntity): Vehicle {
            return Vehicle(
                vehicleEntity.name,
                VehicleType.valueOf(vehicleEntity.type.uppercase()),
                vehicleEntity.x,
                vehicleEntity.y
            )
        }
    }
}