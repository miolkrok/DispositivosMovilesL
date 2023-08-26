package com.example.primera_view.animiolknova.logic.tracemoe

import android.util.Log
import com.example.primera_view.animiolknova.data.endpoints.TraceEndPoint
import com.example.primera_view.animiolknova.data.entities.heroku.AnimeMCResponse
import com.example.primera_view.animiolknova.data.entities.heroku.SearchMCResponse
import com.example.primera_view.data.connections.ApiConnection
import com.example.primera_view.animiolknova.data.entities.tracemoe.Result

class TraceC {
    suspend private fun searchAnimeByID(id: String): SearchMCResponse? {
        var data: SearchMCResponse? = null
        try {
            val service = ApiConnection.getService(ApiConnection.typeApi.Trace,TraceEndPoint::class.java)
            val response = service.searchAnimeByID(id)
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

    suspend private fun getAnimeByID(id: String): AnimeMCResponse? {
        var data: AnimeMCResponse? = null
        try {
            val service = ApiConnection.getService(ApiConnection.typeApi.Trace,TraceEndPoint::class.java)
            val response = service.getAnimeByID(id)
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

    suspend fun generarDetalles(item: Result): AnimeMCResponse? {
        var tmp: SearchMCResponse? = null
        var tmp2: AnimeMCResponse? = null
        tmp = TraceC().searchAnimeByID(item.anilist?.title?.romaji.toString())
        if(tmp?.size==0){
            return null
        }
        tmp2 = TraceC().getAnimeByID(tmp?.get(0)?.id.toString())
        return tmp2
    }
}