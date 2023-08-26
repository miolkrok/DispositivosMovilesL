package com.example.primera_view.animiolknova.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.primera_view.animiolknova.data.connection.FavoriteDB
import com.example.primera_view.animiolknova.data.dao.FavoriteDAO


    @Database(
        entities = [FavoriteDB::class],
        version = 1,
        exportSchema = false
    )
    abstract class FavoriteRepositoryDB : RoomDatabase() {
        abstract fun getFavoriteDAO(): FavoriteDAO
    }

    class FavoriteConexion(){
        fun getConnection(context: Context) =  Room.databaseBuilder(
            context,
            FavoriteRepositoryDB::class.java,
            "DBAniMiolk")
            .build()

    }
