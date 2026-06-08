package com.mobil.modul5compose

import android.app.Application
import androidx.room.Room
import com.mobil.modul5compose.data.SettingsRepository
import com.mobil.modul5compose.data.local.AppDatabase
import com.mobil.modul5compose.network.MovieRepository
import com.mobil.modul5compose.network.TmdbHttpClientFactory
import timber.log.Timber

class Modul5Application : Application() {
    lateinit var settingsRepository: SettingsRepository
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        settingsRepository = SettingsRepository(this)

        val database = Room.databaseBuilder(this, AppDatabase::class.java, "movie_db")
            .fallbackToDestructiveMigration(false)
            .build()

        val httpClient = TmdbHttpClientFactory(BuildConfig.READ_ACCESS_TOKEN).create()
        movieRepository = MovieRepository(httpClient, database.movieDao(), settingsRepository)
    }
}