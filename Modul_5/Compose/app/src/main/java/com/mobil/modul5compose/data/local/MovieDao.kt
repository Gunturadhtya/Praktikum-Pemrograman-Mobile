package com.mobil.modul5compose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE languageTag = :languageTag ORDER BY cachedAt DESC")
    fun getAllMovies(languageTag: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId AND languageTag = :languageTag")
    fun getMovieById(movieId: Int, languageTag: String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun clearAllMovies()
}