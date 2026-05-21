package com.mobil.modul5compose

import android.os.Bundle
import android.os.Environment
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.mobil.modul5compose.navigation.AppNavigation
import com.mobil.modul5compose.ui.theme.Modul3ComposeTheme
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        plant(Timber.DebugTree())
        setContent {
            Modul3ComposeTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}