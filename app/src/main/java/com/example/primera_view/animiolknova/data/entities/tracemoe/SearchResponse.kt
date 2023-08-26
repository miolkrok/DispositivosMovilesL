package com.moncayo.pilco.anisham.model.entities.api.anime

data class SearchResponse(
    val error: String? = null,
    val frameCount: Int? = null,
    val result: List<Result>? = null
)