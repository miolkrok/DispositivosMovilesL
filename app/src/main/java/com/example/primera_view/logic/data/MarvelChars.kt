package com.example.primera_view.logic.data

import android.os.Parcelable
import com.example.primera_view.data.entities.marvel.characters.database.MarvelCharsDB
import kotlinx.parcelize.Parcelize

@Parcelize
class MarvelChars(val id: Int,
                  val name: String,
                  val comic:String,
//                  val synopsis: String,
                  val image: String
                  ) :Parcelable

fun MarvelChars.getMarvelCharsDB() : MarvelCharsDB {

    return MarvelCharsDB(
        id,
        name,
        comic,
//        synopsis,
        image
    )
}