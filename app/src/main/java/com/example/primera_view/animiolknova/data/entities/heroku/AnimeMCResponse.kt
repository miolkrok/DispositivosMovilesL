package com.moncayo.pilco.anisham.model.entities.api.monosChinos

data class AnimeMCResponse(
    val banner: String?=null,
    val date: String?=null,
    val episodes: List<Episode>?=null,
    val genders: List<String>?=null,
    val image: String?=null,
    val rating: String?=null,
    val sinopsis: String?=null,
    val status: String?=null,
    val title: String?=null,
    val titleAlt: String?=null
)