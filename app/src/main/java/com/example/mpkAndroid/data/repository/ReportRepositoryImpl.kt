package com.example.mpkAndroid.data.repository

import com.example.mpkAndroid.data.network.reportsService.ReportsApiService
import com.example.mpkAndroid.domain.ReportRepository
import com.example.mpkAndroid.domain.model.Report
import com.example.mpkAndroid.domain.model.ReportStaticFactory
import com.example.mpkAndroid.domain.model.ReportType
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportsApiService: ReportsApiService
) : ReportRepository {
    override suspend fun getReports(): Collection<Report> {
        val retrievedReports = reportsApiService.getAccidents().values
        val reports: MutableList<Report> = emptyList<Report>().toMutableList()
        retrievedReports.forEach {
            reports.add(ReportStaticFactory.create(it))
        }

        return reports
    }

    override suspend fun publishReport(report: Report) {
        reportsApiService.publishAccident(report.authorEmail, report.type.ordinal, report.latitude, report.longitude)
    }


}