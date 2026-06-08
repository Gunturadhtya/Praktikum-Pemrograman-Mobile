package com.mobil.modul5compose.util

import com.mobil.modul5compose.data.MovieResponse
import com.mobil.modul5compose.data.local.MovieEntity

fun MovieResponse.toEntity(languageTag: String): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title ?: this.originalTitle ?: "Unknown",
        overview = this.overview ?: "",
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        languageTag = languageTag,
        cachedAt = System.currentTimeMillis()
    )
}