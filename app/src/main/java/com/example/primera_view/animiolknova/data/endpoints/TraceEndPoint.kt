package com.example.primera_view.animiolknova.data.endpoints

import com.example.primera_view.animiolknova.data.entities.heroku.AnimeMCResponse
import com.example.primera_view.animiolknova.data.entities.heroku.SearchMCResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TraceEndPoint {
    @GET("search/{id}")
    suspend fun searchAnimeByID(
        @Path("id") id: String
    ): Response<SearchMCResponse>

    @GET("anime/{id}")
    suspend fun getAnimeByID(
        @Path("id") id: String
    ): Response<AnimeMCResponse>
}