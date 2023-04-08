package com.example.mpkAndroid.domain.model

data class Vehicle (
    val number: String,
    val type: VehicleType,
    val latitude: Double,
    val longitude: Double
    )

enum class VehicleType {
    BUS, TRAM
}