package com.example.primera_view.data.endpoints

import com.example.primera_view.data.entities.marvel.characters.MarvelApiChars
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelEndPoint {

    @GET("characters")
    suspend fun getCharactersStartWith(
        @Query("nameStartsWith") name: String,
        @Query("limit") limit: Int,
        @Query("ts") ts:String= "uce",
        @Query("apikey") apikey: String = "48ed26ff242038147ce24450236a7ec2",
        @Query("hash") hash: String="f00af94ad24dd1d56b2ea26ae903030e"
    ): Response<MarvelApiChars>

}