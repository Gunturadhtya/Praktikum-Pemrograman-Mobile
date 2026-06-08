package com.mobil.modul5compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mobil.modul5compose.data.SettingsRepository
import com.mobil.modul5compose.data.local.MovieDao
import com.mobil.modul5compose.network.MovieRepository
import com.mobil.modul5compose.ui.screens.detail.DetailScreen
import com.mobil.modul5compose.ui.screens.detail.DetailViewModel
import com.mobil.modul5compose.ui.screens.detail.DetailViewModelFactory
import com.mobil.modul5compose.ui.screens.home.HomeScreen
import com.mobil.modul5compose.ui.screens.home.HomeViewModel
import com.mobil.modul5compose.ui.screens.home.HomeViewModelFactory
import com.mobil.modul5compose.ui.screens.language.LanguageScreen
import com.mobil.modul5compose.ui.screens.language.LanguageViewModel
import com.mobil.modul5compose.ui.screens.language.LanguageViewModelFactory

@Composable
fun AppNavigation(
    navController: NavHostController,
    settingsRepository: SettingsRepository,
    movieRepository: MovieRepository
) {
    NavHost(navController = navController, startDestination = RoutingNames.HomeScreen) {
        composable<RoutingNames.HomeScreen> {
            val factory = remember { HomeViewModelFactory(movieRepository) }
            val viewModel: HomeViewModel = viewModel(factory = factory)
            HomeScreen(navController, viewModel)
        }
        composable<RoutingNames.DetailScreen> { navBackStack ->
            val detailArgs = navBackStack.toRoute<RoutingNames.DetailScreen>()
            val factory = remember { DetailViewModelFactory(movieRepository, detailArgs.movieId) }
            val viewModel: DetailViewModel = viewModel(factory = factory)
            DetailScreen(viewModel) { navController.popBackStack() }
        }
        composable<RoutingNames.LanguageScreen> {
            val factory = remember { LanguageViewModelFactory(settingsRepository) }
            val viewModel: LanguageViewModel = viewModel(factory = factory)
            LanguageScreen(navController, viewModel)
        }
    }
}