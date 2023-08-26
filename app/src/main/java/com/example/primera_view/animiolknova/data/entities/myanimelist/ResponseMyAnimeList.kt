package com.moncayo.pilco.anisham.model.entities.api.myAnimeList

data class ResponseMyAnimeList(
    val genres: List<Genre>,
    val id: Int,
    val main_picture: MainPicture,
    val mean: Double,
    val rating: String,
    val synopsis: String,
    val title: String
)