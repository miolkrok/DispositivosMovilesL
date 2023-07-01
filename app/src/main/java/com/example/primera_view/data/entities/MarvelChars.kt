package com.example.primera_view.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MarvelChars(val id: Int,
                  val name: String,
                  val comic:String,
                  val image: String
                  ) :Parcelable {

}