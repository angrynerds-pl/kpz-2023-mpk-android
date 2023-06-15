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

    fun addNewReport(report: Report) {
        //TODO
    }

}