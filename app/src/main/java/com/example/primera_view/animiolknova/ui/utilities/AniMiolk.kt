package com.example.primera_view.animiolknova.ui.utilities

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.primera_view.animiolknova.data.repository.FavoriteConexion
import com.example.primera_view.animiolknova.data.repository.FavoriteRepositoryDB

class AniMiolk: Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    @SuppressLint("StaticFieldLeak")
    companion object {
        private val dbCon: FavoriteRepositoryDB? = null
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        fun getConn(): FavoriteRepositoryDB {
            return dbCon ?: return FavoriteConexion().getConnection(context!!)
        }
    }
}