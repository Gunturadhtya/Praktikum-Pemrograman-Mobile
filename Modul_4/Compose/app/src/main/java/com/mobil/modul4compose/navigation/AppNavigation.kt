package com.mobil.modul4compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mobil.modul4compose.data.ProblemRepository
import com.mobil.modul4compose.ui.screens.detail.DetailScreen
import com.mobil.modul4compose.ui.screens.detail.DetailViewModel
import com.mobil.modul4compose.ui.screens.detail.DetailViewModelFactory
import com.mobil.modul4compose.ui.screens.home.HomeScreen
import com.mobil.modul4compose.ui.screens.home.HomeViewModel
import com.mobil.modul4compose.ui.screens.home.HomeViewModelFactory
import com.mobil.modul4compose.ui.screens.language.LanguageScreen
import com.mobil.modul4compose.ui.screens.language.LanguageViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RoutingNames.HomeScreen) {
        composable<RoutingNames.HomeScreen>{
            val factory = remember { HomeViewModelFactory(ProblemRepository, "Modul4Compose") }
            val viewModel: HomeViewModel = viewModel(factory = factory)
            HomeScreen(navController, viewModel)
        }
        composable<RoutingNames.DetailScreen>{ navBackStack ->
            val detailArgs = navBackStack.toRoute<RoutingNames.DetailScreen>()
            val factory = remember { DetailViewModelFactory(detailArgs.problemId, "Modul4Compose") }
            val viewModel: DetailViewModel = viewModel(factory = factory)

            DetailScreen(viewModel,
                { navController.popBackStack() }
            )
        }
        composable<RoutingNames.LanguageScreen> {
            val viewModel: LanguageViewModel = viewModel()
            LanguageScreen(navController, viewModel)
        }
    }
}