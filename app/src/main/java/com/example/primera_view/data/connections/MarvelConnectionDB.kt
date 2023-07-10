package com.example.primera_view.data.connections

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.primera_view.data.dao.marvel.MarvelCharsDAO
import com.example.primera_view.data.entities.marvel.characters.database.MarvelCharsDB

@Database(
    entities = [MarvelCharsDB::class],
    version = 1
)
abstract class MarvelConnectionDB: RoomDatabase() {

    abstract fun marvelDao(): MarvelCharsDAO
}