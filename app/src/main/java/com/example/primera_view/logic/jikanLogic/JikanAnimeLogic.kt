package com.example.primera_view.logic.jikanLogic

import com.example.primera_view.data.connections.ApiConnection
import com.example.primera_view.data.endpoints.JikanEndpoint
import com.example.primera_view.logic.data.MarvelChars

class JikanAnimeLogic {

    suspend fun getAllAnimes() : List<MarvelChars>{

        var itemList = arrayListOf<MarvelChars>()

        val response = ApiConnection.getService(ApiConnection.typeApi.Jikan, JikanEndpoint::class.java).getAllAnimes()

        if(response.isSuccessful) {
            response.body()!!.data.forEach {
                val m = MarvelChars(
                    it.mal_id,
                    it.title,
                    it.titles[0].title,
                    it.images.jpg.image_url

                )
                itemList.add(m)
            }
        }
        return  itemList
    }
}