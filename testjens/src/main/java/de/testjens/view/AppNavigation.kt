package de.testjens.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.testjens.model.Game
import de.testjens.view.screens.ScreenJoinGameHandler
import de.testjens.view.screens.ScreenStartHandler
import de.testjens.viewmodel.JoinGameViewModel
import kotlin.random.Random

sealed class NavScreen(val route : String, val add : String = ""){
    object Start : NavScreen("start")
    object JoinGame : NavScreen("joingame")
}

@Composable
fun AppNavigation(
    viewModel: JoinGameViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.Start.route){
        composable(NavScreen.Start.route){
            ScreenStartHandler(
                viewModel = viewModel,
                clickNewGame = {navController.navigate(NavScreen.JoinGame.route)},
                clickJoinGame = {navController.navigate(NavScreen.JoinGame.route)}
            )
        }
        composable(NavScreen.JoinGame.route){
            ScreenJoinGameHandler(
                viewModel = viewModel,
//                clickJoinGame = {navController.navigate(NavScreen.Start.route)}
                clickJoinGame = {viewModel.addGame(Game("1"))}
//                clickJoinGame = {viewModel.test()}
            )
        }
    }
}