package com.example.primera_view.data.entities.marvel.characters.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.primera_view.logic.data.MarvelChars
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class MarvelCharsDB(
    @PrimaryKey
    val id: Int,
    val name: String,
    val comic:String,
//    val synopsis: String,
    val image: String
) : Parcelable

fun MarvelCharsDB.getMarvelChars() : MarvelChars {

    return MarvelChars(
        id,
        name,
        comic,
//        synopsis,
        image
    )
}