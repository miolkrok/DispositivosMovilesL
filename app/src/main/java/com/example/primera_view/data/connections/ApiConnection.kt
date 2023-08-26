package com.example.primera_view.data.connections

import android.annotation.SuppressLint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConnection {

    enum class typeApi {
        Jikan, Marvel, Trace, Heroku,Animelist
    }

    private val API_JIKAN = "https://api.jikan.moe/v4/"
    private val API_MARVEL = "https://gateway.marvel.com/v1/public/"
    private val API_TRACE = "https://api.trace.moe/"
    private val API_HEROKU = "https://dnsdm.herokuapp.com/"
    private val API_MYANIMELIST ="https://api.myanimelist.net/v2/"

    @SuppressLint("SuspiciousIndentation")
    private fun getJikanConection(base: String): Retrofit  {
    var retrofit = Retrofit.Builder()
        .baseUrl(base)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        return retrofit
    }

    suspend fun <T,E: Enum<E>> getService(api: E, service: Class<T>): T {
        var BASE = " "
        when(api.name){
            typeApi.Jikan.name -> {
                BASE = API_JIKAN
            }

            typeApi.Marvel.name -> {
                BASE = API_MARVEL
            }

            typeApi.Trace.name -> {
                BASE = API_TRACE
            }

            typeApi.Heroku.name -> {
                BASE = API_HEROKU
            }
            typeApi.Animelist.name -> {
                BASE = API_MYANIMELIST
            }

        }
        return getJikanConection(BASE).create(service)

    }
}