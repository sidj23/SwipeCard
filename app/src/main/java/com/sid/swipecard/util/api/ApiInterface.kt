package com.sid.swipecard.util.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @GET("fjaqJ")
    fun makeGetCardData(): Call<String?>?
}