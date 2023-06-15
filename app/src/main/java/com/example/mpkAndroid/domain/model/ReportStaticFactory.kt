package com.example.mpkAndroid.domain.model

import com.example.mpkAndroid.data.model.ReportEntity

class ReportStaticFactory {
    companion object {
        fun create(
            reportEntity: ReportEntity
        ): Report {
            return Report(
                reportEntity.publisherName,
                reportEntity.publisherName,
                ReportType.valueOf(reportEntity.type.uppercase()),
                reportEntity.latitude,
                reportEntity.longitude
            )
        }
    }
}