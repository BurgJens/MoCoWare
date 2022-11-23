package de.testjens.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.testjens.view.screens.ScreenJoinGame
import de.testjens.view.screens.ScreenStart
import de.testjens.viewmodel.AppViewModel

sealed class NavScreen(val route : String, val add : String = ""){
    object Start : NavScreen("start")
    object JoinGame : NavScreen("joingame")
}

@Composable
fun AppNavigation(
    viewModel: AppViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.Start.route){
        composable(NavScreen.Start.route){
            ScreenStart(
                navigateBeitreten = {navController.navigate(NavScreen.JoinGame.route)},
                navigateJoinGame = {navController.navigate(NavScreen.JoinGame.route)}
            )
        }
        composable(NavScreen.JoinGame.route){
            ScreenJoinGame(
                viewModel = viewModel
            )
        }
    }
}