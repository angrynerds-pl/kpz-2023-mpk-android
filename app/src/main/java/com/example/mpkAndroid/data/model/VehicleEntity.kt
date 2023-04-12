package com.example.mpkAndroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VehicleEntity(
    val name: String,
    val type: String,
    val y: Double,
    val x: Double,
    val k: Double
)