package com.example.primera_view.animiolknova.data.connection

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.example.primera_view.animiolknova.data.entities.tracemoe.Result

@Entity
@TypeConverters(Converter::class)
class FavoriteDB(
    @PrimaryKey(autoGenerate = false)
    val idMal: Int,
    val itemFavorites: Result,
) {
    override fun toString(): String {
        return "FavoriteDB(idMal=$idMal, itemFavorites=$itemFavorites)"
    }
}

class Converter {

    @TypeConverter
    fun fromAnilist(r: Result): String {
        return Gson().toJson(r)
    }

    @TypeConverter
    fun toAnilist(resultString: String): Result {
        return Gson().fromJson(resultString, Result::class.java)
    }
}