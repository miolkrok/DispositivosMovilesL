package com.moncayo.pilco.anisham.model.entities.api.anime

data class Anilist(
    val id: Int?=null,
    val idMal: Int?=null,
    val isAdult: Boolean?=null,
    val synonyms: List<String>?=null,
    val title: Title?=null
)