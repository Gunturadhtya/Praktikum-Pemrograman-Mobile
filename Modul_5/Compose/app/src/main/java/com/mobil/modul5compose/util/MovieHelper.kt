package com.mobil.modul5compose.util

import com.mobil.modul5compose.data.MovieResponse
import com.mobil.modul5compose.data.local.MovieEntity

fun MovieResponse.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        cachedAt = System.currentTimeMillis()
    )
}