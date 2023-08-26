package com.example.primera_view.animiolknova.logic.favorites

import android.util.Log
import com.example.primera_view.animiolknova.data.connection.FavoriteDB
import com.example.primera_view.animiolknova.ui.utilities.AniMiolk
import com.example.primera_view.animiolknova.data.entities.tracemoe.Result
class FavoritesC {

    suspend fun saveAnime(item: Result) {
        val conn = AniMiolk.getConn()
        val dao = conn.getFavoriteDAO()
        val itemDB = FavoriteDB(item.anilist!!.idMal!!.toInt(), item)

        Log.i("UCE", itemDB.toString())
        Log.i("UCE", item.toString())
        dao.insertarFavorito(itemDB)
    }

    suspend fun obtenerFavoritos(): List<FavoriteDB> {
        val conn = AniMiolk.getConn()
        val dao = conn.getFavoriteDAO()
        return dao.obtenerTodo()
    }

    suspend fun borrar(numero: Int) {
        val conn = AniMiolk.getConn()
        val dao = conn.getFavoriteDAO()
        dao.eliminarFavorito(dao.getAnimne(numero))
    }

    suspend fun buscarFavoritos(numero: Int): Boolean {
        val conn = AniMiolk.getConn()
        val dao = conn.getFavoriteDAO()
        if (dao.getAnimne(numero) != null) {
            return true
        } else {
            return false
        }
    }
}