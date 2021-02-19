package com.sid.swipecard.util.api

import com.sid.swipecard.util.BASE_URL
import com.sid.swipecard.util.CUSTOM_CONNECT_TIMEOUT
import com.sid.swipecard.util.CUSTOM_READ_TIMEOUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiUtil {

    // This method is use to initiate and build the httpclient for the Retrofit builder

    private fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(
                CUSTOM_CONNECT_TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                CUSTOM_READ_TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )
        return client.build()
    }

    // This method is use to initiate and build the Retrofit builder.

    fun provideRetrofit(): Retrofit {
        val okHttpClient =
            provideOkhttpClient()
        return Retrofit.Builder()
            .addConverterFactory(
                ScalarsConverterFactory.create()
            )
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}