package com.example.primera_view.animiolknova.data.connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class APIConnectRepo {
    private fun getRetrofitBuilder(base: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> buildTraceMoeService(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://api.trace.moe/")
        return builder.create(service)
    }

    fun <T> buildMonosChinosService(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://dnsdm.herokuapp.com/")
        return builder.create(service)
    }

    fun <T> buildMyAnimeList(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://api.myanimelist.net/v2/")
        return builder.create(service)
    }
}