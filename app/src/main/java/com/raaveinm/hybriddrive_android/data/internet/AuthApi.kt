package com.raaveinm.hybriddrive_android.data.internet

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @GET
    suspend fun answer(): String
    @POST("api/login.php")
    suspend fun login(@Body auth: AuthSerialization): Response<String>
    @POST("api/register.php")
    suspend fun register(@Body auth: AuthSerialization): Response<String>
    @GET("api/logout.php")
    suspend fun logout()
}
