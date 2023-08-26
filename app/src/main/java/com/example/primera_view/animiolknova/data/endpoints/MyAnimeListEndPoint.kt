package com.moncayo.pilco.anisham.model.endPoints

import com.moncayo.pilco.anisham.model.entities.api.monosChinos.SearchMCResponse
import com.moncayo.pilco.anisham.model.entities.api.myAnimeList.ResponseMyAnimeList
import com.moncayo.pilco.anisham.utils.Variables
import retrofit2.Response
import retrofit2.http.*

interface MyAnimeListEndPoint {

    @GET("anime/{idAnime}")
    @Headers("X-MAL-CLIENT-ID:"+Variables.ClientID)
    suspend fun searchAnimeByID(
        @Path("idAnime") id: String,
        @Query("fields") seleccionDatos:String
    ): Response<ResponseMyAnimeList>

}