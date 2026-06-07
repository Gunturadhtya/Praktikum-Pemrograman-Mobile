package com.mobil.modul5compose.network

import com.mobil.modul5compose.data.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val client: HttpClient) : BaseRepository() {
    suspend fun getMovie(movieId: Int): Flow<Result<MovieResponse>> {
        return safeApiFlow {
            client.get("movie/$movieId?language=en-US")
        }
    }
}