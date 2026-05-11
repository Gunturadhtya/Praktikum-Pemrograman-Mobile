package com.mobil.modul3compose.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mobil.modul3compose.ui.screens.detail.DetailScreen
import com.mobil.modul3compose.ui.screens.detail.DetailViewModel
import com.mobil.modul3compose.ui.screens.home.HomeScreen
import com.mobil.modul3compose.ui.screens.home.HomeViewModel
import com.mobil.modul3compose.ui.screens.language.LanguageScreen
import com.mobil.modul3compose.ui.screens.language.LanguageViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RoutingNames.HomeScreen) {
        composable<RoutingNames.HomeScreen>{
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(navController, viewModel)
        }
        composable<RoutingNames.DetailScreen>{ navBackStack ->
            val detailArgs = navBackStack.toRoute<RoutingNames.DetailScreen>()
            val viewModel: DetailViewModel = viewModel()

            DetailScreen(
                detailArgs.problemId,
                viewModel,
                { navController.popBackStack() }
            )
        }
        composable<RoutingNames.LanguageScreen> {
            val viewModel: LanguageViewModel = viewModel()
            LanguageScreen(navController, viewModel)
        }
    }
}