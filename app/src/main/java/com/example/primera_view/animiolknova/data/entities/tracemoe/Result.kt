package com.moncayo.pilco.anisham.model.entities.api.anime

import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist

data class Result(
    val anilist: Anilist?=null,
    val episode: Number?=null,
    val filename: String?=null,
    val from: Double?=null,
    val image: String?=null,
    val similarity: Double?=null,
    val to: Double?=null,
    val video: String?=null
)