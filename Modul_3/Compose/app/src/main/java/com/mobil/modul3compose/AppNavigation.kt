package com.mobil.modul3compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

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
                {navController.popBackStack()}
            )
        }
        composable<RoutingNames.LanguageScreen> {
            val viewModel: LanguageViewModel = viewModel()
            LanguageScreen(navController, viewModel)
        }
    }
}