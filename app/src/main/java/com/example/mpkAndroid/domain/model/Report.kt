package com.example.mpkAndroid.domain.model

data class Report(
    val authorEmail: String,
    val authorName: String,
    val type: ReportType,
    val latitude: Double,
    val longitude: Double
)

enum class ReportType(val translation: String) {
    ACCIDENT("Wypadek"),
    DERAILMENT("Wykolejenie"),
    MALFUNCTION("Awaria"),
    DAMAGED_TRACKS("Uszkodzone tory")
}