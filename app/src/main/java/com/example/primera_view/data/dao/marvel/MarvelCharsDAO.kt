package com.example.primera_view.data.dao.marvel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.primera_view.data.entities.marvel.characters.database.MarvelCharsDB

@Dao
interface MarvelCharsDAO {

    @Query("select * from MarvelCharsDB")
    fun getAllCharacters() : List<MarvelCharsDB>

    @Query("select * from MarvelCharsDB where id = :id")
    fun getOneCharacter(id: Int): MarvelCharsDB

    @Insert
    fun insertMarvelChar(ch: List<MarvelCharsDB>)

//    @Delete
//    fun eliminarMarvelChar(pk: Int):MarvelCharsDB
}