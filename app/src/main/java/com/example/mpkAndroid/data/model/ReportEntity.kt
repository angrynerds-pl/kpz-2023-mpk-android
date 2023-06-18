package com.example.mpkAndroid.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportEntity(
    @SerialName("\$id") val dollarId: Int,
    val id: Int,
    val type: Int,
    val publisherId: Int,
    val publisherName: String,
    val ratingId: Int,
    val latitude: Double,
    val longitude: Double,
    val registrationTime: String,
    val validTime: String
)