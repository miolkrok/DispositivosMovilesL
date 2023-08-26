package com.example.primera_view.animiolknova.data.endpoints

import com.example.primera_view.animiolknova.data.entities.myanimelist.ResponseMyAnimeList
import com.example.primera_view.animiolknova.ui.utilities.AniMiolkVariables
import retrofit2.Response
import retrofit2.http.*

interface MyAnimeListEndPoint {

    @GET("anime/{idAnime}")
    @Headers("X-MAL-CLIENT-ID:"+AniMiolkVariables.ClientID)
    suspend fun searchAnimeByID(
        @Path("idAnime") id: String,
        @Query("fields") seleccionDatos:String
    ): Response<ResponseMyAnimeList>

}