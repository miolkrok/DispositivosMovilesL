package com.example.primera_view.logic.jikanLogic

import android.util.Log
import com.example.primera_view.data.connections.ApiConnection
import com.example.primera_view.data.endpoints.MarvelEndPoint
import com.example.primera_view.data.entities.marvel.characters.database.MarvelCharsDB
import com.example.primera_view.data.entities.marvel.characters.database.getMarvelChars
import com.example.primera_view.data.entities.marvel.characters.getMarvelChars
import com.example.primera_view.logic.data.MarvelChars
import com.example.primera_view.logic.data.getMarvelCharsDB
import com.example.primera_view.ui.utilities.Primeraview
import java.lang.Exception
import java.lang.RuntimeException


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

    suspend fun getAllMarvelCharsDB() : List<MarvelChars>{
        var itemList= arrayListOf<MarvelChars>()
        val itemsAux = Primeraview.getDbInstance().marvelDao().getAllCharacters()
        itemsAux.forEach {
            itemList.add(
                it.getMarvelChars()
            )
        }
        return itemList

    }
    suspend fun getInitChars(page: Int):MutableList<MarvelChars> {
        var items = mutableListOf<MarvelChars>()
        try {
            items = MarvelLogic()
                .getAllMarvelCharsDB().
                toMutableList()
            if(items.isEmpty()){
                items = (MarvelLogic().getAllMarvelChars(
                    0,page * 3))
                MarvelLogic().insertMarvelCharstoDB(items)
            }
        }catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
        return items
    }

    suspend fun insertMarvelCharstoDB(items: List<MarvelChars>) {
        var itemsDB = arrayListOf<MarvelCharsDB>()
        items.forEach {
            itemsDB.add(it.getMarvelCharsDB())
        }
        Primeraview.getDbInstance().marvelDao().insertMarvelChar(itemsDB)
    }
}