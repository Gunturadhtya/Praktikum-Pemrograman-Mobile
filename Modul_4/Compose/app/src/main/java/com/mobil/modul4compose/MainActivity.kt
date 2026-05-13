package com.mobil.modul4compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.mobil.modul4compose.navigation.AppNavigation
import com.mobil.modul4compose.ui.theme.Modul3ComposeTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3ComposeTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}