package com.example.mpkAndroid.domain

import com.example.mpkAndroid.domain.model.Report

interface ReportRepository {

    suspend fun getReports(): Collection<Report>

    suspend fun publishReport(report: Report)
}