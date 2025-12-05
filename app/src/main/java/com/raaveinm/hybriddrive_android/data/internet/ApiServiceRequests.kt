package com.raaveinm.hybriddrive_android.data.internet

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiServiceRequests {
    @GET("api/get_files.php")
    suspend fun getFiles(): List<FileList>

    @Streaming
    @GET
    suspend fun downloadFile(@Url url: String): ResponseBody

    @Multipart
    @POST("api/upload.php")
    suspend fun uploadFile(@Part file: MultipartBody.Part)
}