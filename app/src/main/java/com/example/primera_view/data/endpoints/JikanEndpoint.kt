package com.example.primera_view.data.endpoints

import com.example.primera_view.data.entities.jikan.JikanAnimeEntity
import retrofit2.Response
import retrofit2.http.GET

interface JikanEndpoint {

    @GET("top/anime")
    suspend fun getAllAnimes(): Response<JikanAnimeEntity>
}