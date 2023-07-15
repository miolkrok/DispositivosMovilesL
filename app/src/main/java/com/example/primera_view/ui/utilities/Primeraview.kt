package com.example.primera_view.ui.utilities

import android.app.Application
import androidx.room.Room
import com.example.primera_view.data.connections.MarvelConnectionDB
import com.example.primera_view.data.entities.marvel.characters.database.MarvelCharsDB

class Primeraview: Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            MarvelConnectionDB::class.java,
            "marvelDB").build()

    }
    companion object{
        private var db:MarvelConnectionDB? = null

        fun getDbInstance() : MarvelConnectionDB  {
            return db!!
        }

    }



}