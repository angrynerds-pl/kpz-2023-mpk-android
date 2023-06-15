package com.example.mpkAndroid.data.network.reportsService

import com.example.mpkAndroid.data.model.ReportEntity
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.http.GET

@ViewModelScoped
interface ReportsApiService {

    @GET("accidents/GetAccidents")
    suspend fun getAccidents(): Collection<ReportEntity>
}