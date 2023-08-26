package com.example.primera_view.animiolknova.data.entities.myanimelist


data class ResponseMyAnimeList(
    val genres: List<Genre>,
    val id: Int,
    val main_picture: MainPicture,
    val mean: Double,
    val rating: String,
    val synopsis: String,
    val title: String
)