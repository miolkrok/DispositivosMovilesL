package com.example.primera_view.logic.jikanLogic

import com.example.primera_view.data.connections.ApiConnection
import com.example.primera_view.data.endpoints.JikanEndpoint
import com.example.primera_view.data.entities.MarvelChars

class JikanAnimeLogic {

    suspend fun getAllAnimes() : List<MarvelChars>{
        var call = ApiConnection.getJikanConection()
        val response = call.create(JikanEndpoint::class.java).getAllAnimes()

        var itemList = arrayListOf<MarvelChars>()

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