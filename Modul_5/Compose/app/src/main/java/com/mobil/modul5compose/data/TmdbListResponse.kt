package com.mobil.modul5compose.data

import kotlinx.serialization.Serializable

@Serializable
data class TmdbListResponse(
    val results: List<MovieResponse>
)