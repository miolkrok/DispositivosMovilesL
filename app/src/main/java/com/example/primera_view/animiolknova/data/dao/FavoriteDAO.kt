package com.example.primera_view.animiolknova.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.primera_view.animiolknova.data.connection.FavoriteDB

@Dao
interface FavoriteDAO {
    @Query("select * from FavoriteDB")
    fun obtenerTodo(): List<FavoriteDB>

    @Query("select * from FavoriteDB where idMal = :idAnime")
    fun getAnimne(idAnime: Int): FavoriteDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarFavorito(a: FavoriteDB)

    @Delete
    fun eliminarFavorito(a: FavoriteDB)
}