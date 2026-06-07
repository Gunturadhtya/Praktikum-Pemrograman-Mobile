package com.mobil.modul5compose.network

import com.mobil.modul5compose.data.MovieResponse
import com.mobil.modul5compose.data.Resource
import com.mobil.modul5compose.data.SettingsRepository
import com.mobil.modul5compose.data.TmdbListResponse
import com.mobil.modul5compose.data.local.MovieDao
import com.mobil.modul5compose.data.local.MovieEntity
import com.mobil.modul5compose.util.toEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val client: HttpClient,
    private val movieDao: MovieDao,
    private val settingsRepository: SettingsRepository
) : BaseRepository() {

    private val languageQuery: String
        get() = "${settingsRepository.getLanguageTag()}-${settingsRepository.getLanguageTag().uppercase()}"


    suspend fun getPopularMovies(): Flow<Resource<List<MovieEntity>>> {
        return networkBoundResource(
            query = {
                movieDao.getAllMovies()
            },
            fetch = {
                val response = client.get("movie/popular?language=$languageQuery")
                if(!response.status.isSuccess()){
                    throw Exception("API Error: ${response.status.value}")
                }
                response.body<TmdbListResponse>()
            },
            saveFetchResult = { response ->
                movieDao.insertMovie(response.results.map { it.toEntity() })
            },
            shouldFetch = { cachedList ->
                val cacheExpirationMillis = 24 * 60 * 60 * 1000L
                cachedList.isNullOrEmpty() || (System.currentTimeMillis() - cachedList.first().cachedAt > cacheExpirationMillis)

            }
        ).flowOn(Dispatchers.IO)
    }

    suspend fun getMovie(movieId: Int): Flow<Resource<MovieEntity>> {
        return networkBoundResource(
            query = {
                movieDao.getMovieById(movieId).filterNotNull()
            },
            fetch = {
                val response = client.get("movie/$movieId?language=$languageQuery")
                if(!response.status.isSuccess()){
                    throw Exception("API Error: ${response.status.value}")
                }
                response.body<MovieResponse>()
            },
            saveFetchResult = { response ->
                movieDao.insertMovie(response.toEntity())
            },
            shouldFetch = { cachedEntity ->
                val cacheExpirationMillis = 24 * 60 * 60 * 1000L
                cachedEntity == null || (System.currentTimeMillis() - cachedEntity.cachedAt > cacheExpirationMillis)

            }
        ).flowOn(Dispatchers.IO)
    }
}