package com.example.mpkAndroid.di

import com.example.mpkAndroid.data.network.reportsService.ReportsApiService
import com.example.mpkAndroid.data.network.vehiclesPositionsApiService.VehiclesPositionsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Module
@InstallIn(
    ActivityRetainedComponent::class
)
object DataModule {

    @Provides
    fun providesVehiclesPositionsApiService(): VehiclesPositionsApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mpk.wroc.pl/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

        return retrofit.create(VehiclesPositionsApiService::class.java)
    }

    @Provides
    fun providesReportsApiService(): ReportsApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://10.0.2.2:7036/")
            .client(getUnsafeOkHttpClient()?.build())
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

        return retrofit.create(ReportsApiService::class.java)
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}