package com.example.mpkAndroid.data.repository

import com.example.mpkAndroid.data.network.reportsService.ReportsApiService
import com.example.mpkAndroid.domain.ReportRepository
import com.example.mpkAndroid.domain.model.Report
import com.example.mpkAndroid.domain.model.ReportStaticFactory
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportsApiService: ReportsApiService
) : ReportRepository {
    override suspend fun getReports(): Collection<Report> {
        val retrievedReports = reportsApiService.getAccidents()
        val reports: MutableList<Report> = emptyList<Report>().toMutableList()
        retrievedReports.forEach {
            reports.add(ReportStaticFactory.create(it))
        }

        return reports
    }


}