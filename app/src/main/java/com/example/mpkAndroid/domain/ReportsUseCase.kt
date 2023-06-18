package com.example.mpkAndroid.domain;

import com.example.mpkAndroid.domain.model.Report
import com.example.mpkAndroid.domain.model.ReportType
import javax.inject.Inject;

class ReportsUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend fun getReports(): Collection<Report> {
        return reportRepository.getReports()
    }

    suspend fun addNewReport(report: Report) {
        reportRepository.publishReport(report)
    }

}