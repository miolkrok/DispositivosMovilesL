package com.example.primera_view.animiolknova.data.endpoints

import com.example.primera_view.animiolknova.data.entities.tracemoe.SearchResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface SearchEndPoint {
    @GET("search")
    suspend fun searchByURL(
        @Query("anilistInfo") anilistInfo: String,
        @Query("url") url: String,
    ): Response<SearchResponse>

    @Multipart
    @POST("search")
    fun searchWithFile(
        @Query("anilistInfo") anilistInfo: String,
        @Part image: MultipartBody.Part,
    ): Response<SearchResponse>

    @POST("search?cutBorders&anilistInfo")
    suspend fun conImg(
        @HeaderMap headers: Map<String, String>,
        @Body image: RequestBody,
    ): Response<SearchResponse>
}