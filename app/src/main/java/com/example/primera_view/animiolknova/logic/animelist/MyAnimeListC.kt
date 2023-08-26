package com.example.primera_view.animiolknova.logic.animelist


import android.util.Log
import com.example.primera_view.animiolknova.data.endpoints.MyAnimeListEndPoint
import com.example.primera_view.animiolknova.data.entities.heroku.AnimeMCResponse
import com.example.primera_view.animiolknova.data.entities.myanimelist.ResponseMyAnimeList
import com.example.primera_view.data.connections.ApiConnection
import java.util.Collections
import java.util.stream.Collectors

class MyAnimeListUC {

    suspend fun obtenerAnime(id: String): ResponseMyAnimeList? {
        var data: ResponseMyAnimeList? = null
        try {
            val service = ApiConnection.getService(MyAnimeListEndPoint::class.java)
            val response = service.searchAnimeByID(id, "title,synopsis,mean,genres,rating")
            if (response.isSuccessful) {
                data = response.body()!!
            } else {
                throw Exception("Fracaso en la conexion")
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return data
    }

     fun convertirMyanimeList(item: ResponseMyAnimeList): AnimeMCResponse {
        val generos: List<String> =
            item.genres.stream().map { a -> a.name }.collect(Collectors.toList())
        val datos = AnimeMCResponse(
            item.main_picture.medium.toString(),
            null,
            null,
            generos,
            item.main_picture.large.toString(),
            (item.mean/2).toString(),
            item.synopsis,
            null,
            item.title,
            item.title
        )
        return datos
    }
}