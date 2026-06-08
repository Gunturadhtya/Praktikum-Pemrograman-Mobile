package com.mobil.modul5compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.mobil.modul5compose.navigation.AppNavigation
import com.mobil.modul5compose.ui.theme.Modul3ComposeTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as Modul5Application

        setContent {
            Modul3ComposeTheme {
                val navController = rememberNavController()
                AppNavigation(
                    navController = navController,
                    settingsRepository = app.settingsRepository,
                    movieRepository = app.movieRepository
                )
            }
        }
    }
}