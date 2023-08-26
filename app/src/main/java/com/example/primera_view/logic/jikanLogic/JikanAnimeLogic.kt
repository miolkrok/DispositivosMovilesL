package com.example.primera_view.logic.jikanLogic

import android.util.Log
import com.example.primera_view.animiolknova.data.endpoints.SearchEndPoint
import com.example.primera_view.animiolknova.data.entities.tracemoe.SearchResponse
import com.example.primera_view.data.connections.ApiConnection
import com.example.primera_view.data.endpoints.JikanEndpoint
import com.example.primera_view.logic.data.MarvelChars
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File

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

    suspend fun getAnimeWithImg(file: File): SearchResponse? {
        var data: SearchResponse? = null
        try {
            val headerMap = mutableMapOf<String, String>()
            headerMap["accept"] = "application/json"
            val fileReqBody = RequestBody.create(MediaType.parse("application/octet-stream"), file)
            //val image = MultipartBody.Part.createFormData("image", file.name, fileReqBody)
            val service = ApiConnection.getService(ApiConnection.typeApi.Trace, SearchEndPoint::class.java)
            val response = service.conImg(headerMap, fileReqBody)
            if (response.isSuccessful) {
                data = response.body()!!
            } else {
                throw Exception("Fracaso en la conexion")
            }
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }
        return data
    }


}