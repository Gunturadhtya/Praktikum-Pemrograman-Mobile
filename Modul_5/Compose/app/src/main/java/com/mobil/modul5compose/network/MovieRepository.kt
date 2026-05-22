package com.mobil.modul5compose.network

import com.mobil.modul5compose.data.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class MovieRepository(private val client: HttpClient) : BaseRepository() {
    suspend fun getMovie(movieId: Int): Result<MovieResponse> {
        return safeApiCall {
            client.get("movie/$movieId?language=en-US")
        }
    }
}