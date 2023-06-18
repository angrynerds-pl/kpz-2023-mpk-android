package com.example.mpkAndroid.data.network.reportsService

import com.example.mpkAndroid.data.model.ReportEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@Serializable
data class AccidentsResponse(
    @SerialName("\$id") val id: Int,
    @SerialName("\$values") val values: Collection<ReportEntity>
)

@ViewModelScoped
interface ReportsApiService {

    @GET("accident/GetAccidents")
    suspend fun getAccidents(): AccidentsResponse

    @FormUrlEncoded
    @POST("accident/PublishAccident")
    suspend fun publishAccident(
        @Query("UserMail") userMail: String,
        @Field("type") type: Int,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double,
    )
}