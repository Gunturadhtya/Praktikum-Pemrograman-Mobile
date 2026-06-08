package com.mobil.modul5compose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: Int,
    val title: String? = null,
    val overview: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("original_title") val originalTitle: String? = null
)
