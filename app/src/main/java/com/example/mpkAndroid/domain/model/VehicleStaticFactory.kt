package com.example.mpkAndroid.domain.model

import com.example.mpkAndroid.data.model.VehicleEntity

class VehicleStaticFactory {
    companion object {
        fun create(vehicleEntity: VehicleEntity): Vehicle {
            var vehicle: Vehicle? = null
            when (vehicleEntity.type) {
                "bus" -> vehicle = Vehicle(
                    vehicleEntity.name,
                    VehicleType.BUS,
                    vehicleEntity.x,
                    vehicleEntity.y
                )
                "tram" -> vehicle = Vehicle(
                    vehicleEntity.name,
                    VehicleType.TRAM,
                    vehicleEntity.x,
                    vehicleEntity.y
                )
            }
            return vehicle!!
        }
    }
}