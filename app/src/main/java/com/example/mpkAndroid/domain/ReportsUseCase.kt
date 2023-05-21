package com.example.mpkAndroid.domain;

import com.example.mpkAndroid.domain.model.Report
import com.example.mpkAndroid.domain.model.ReportType
import javax.inject.Inject;

class ReportsUseCase @Inject constructor() {
    suspend fun getReports(): Collection<Report> {
        return listOf(
            Report(
                "test",
                "Marian",
                ReportType.ACCIDENT,
                51.11,
                17.04
            )
        )
    }

    fun addNewReport(report: Report) {
        //TODO
    }

}