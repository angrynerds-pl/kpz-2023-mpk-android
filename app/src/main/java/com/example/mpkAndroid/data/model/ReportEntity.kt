package com.example.mpkAndroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReportEntity(
    val id: Int,
    val type: String,
    val publisherId: Int,
    val publisherName: String,
    val ratingId: Int,
    val latitude: Double,
    val longitude: Double,
    val registrationTime: String,
    val validTime: String,
)