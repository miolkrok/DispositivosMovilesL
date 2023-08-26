package com.moncayo.pilco.anisham.model.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class APIRepository {
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

    fun <T> buildUserService(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://gorest.co.in/public/v2/")
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