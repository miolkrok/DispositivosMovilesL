package com.example.primera_view.data.entities.marvel.characters

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)