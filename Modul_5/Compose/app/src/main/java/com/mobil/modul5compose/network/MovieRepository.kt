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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val client: HttpClient,
    private val movieDao: MovieDao,
    private val settingsRepository: SettingsRepository
) : BaseRepository() {

    val languageTag = settingsRepository.languageTag


    fun getPopularMovies(): Flow<Resource<List<MovieEntity>>> {
        val currentLanguage = settingsRepository.getLanguageTag()
        return networkBoundResource(
            query = {
                movieDao.getAllMovies(currentLanguage)
            },
            fetch = {
                val response = client.get("movie/popular?language=$currentLanguage")
                if(!response.status.isSuccess()){
                    throw Exception("API Error: ${response.status.value}")
                }
                response.body<TmdbListResponse>()
            },
            saveFetchResult = { response ->
                movieDao.insertMovie(response.results.map { it.toEntity(currentLanguage) })
            },
            shouldFetch = { cachedList ->
                cachedList.isNullOrEmpty()
            }
        ).flowOn(Dispatchers.IO)
    }

    fun getMovie(movieId: Int): Flow<Resource<MovieEntity>> {
        val currentLanguage = settingsRepository.getLanguageTag()
        return networkBoundResource(
            query = {
                movieDao.getMovieById(movieId, currentLanguage).filterNotNull()
            },
            fetch = {
                val response = client.get("movie/$movieId?language=$currentLanguage")
                if(!response.status.isSuccess()){
                    throw Exception("API Error: ${response.status.value}")
                }
                response.body<MovieResponse>()
            },
            saveFetchResult = { response ->
                movieDao.insertMovie(response.toEntity(currentLanguage))
            },
            shouldFetch = { cachedEntity ->
                cachedEntity == null
            }
        ).flowOn(Dispatchers.IO)
    }


}