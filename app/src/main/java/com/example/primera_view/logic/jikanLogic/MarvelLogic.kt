package com.example.primera_view.logic.jikanLogic

import android.util.Log
import com.example.primera_view.data.connections.ApiConnection
import com.example.primera_view.data.endpoints.MarvelEndPoint
import com.example.primera_view.data.entities.marvel.characters.getMarvelChars
import com.example.primera_view.logic.data.MarvelChars


class MarvelLogic {

    suspend fun getMarvelChars(offset: Int, limit: Int) : ArrayList<MarvelChars>{

        var itemList = arrayListOf<MarvelChars>()

        val response = ApiConnection.getService(
            ApiConnection.typeApi.Marvel,
            MarvelEndPoint::class.java)
            .getAllMarvelChars(offset,limit)
        offset.toString()

//        if (call != null ){
//            val response = call.getCharactersStartWith(name, limit)

        if(response != null) {
            response.body()!!.data.results.forEach {
                val m = it.getMarvelChars()
                itemList.add(m)
            }
        }else {
            Log.d("UCE",response.toString())
        }
        return  itemList
    }


    suspend fun getAllMarvelChars(offset: Int, limit: Int) : ArrayList<MarvelChars>{

        var itemList = arrayListOf<MarvelChars>()

        val response = ApiConnection.getService(
            ApiConnection.typeApi.Marvel,
            MarvelEndPoint::class.java)
            .getAllMarvelChars(offset,limit)
        offset.toString()

//        if (call != null ){
//            val response = call.getCharactersStartWith(name, limit)

            if(response != null) {
                response.body()!!.data.results.forEach {
                    val m = it.getMarvelChars()
                    itemList.add(m)
                }
            }else {
                Log.d("UCE",response.toString())
            }
        return  itemList
    }
}