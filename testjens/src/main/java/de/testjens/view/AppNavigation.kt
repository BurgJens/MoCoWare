package de.testjens.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.testjens.view.screens.ScreenGameHandler
import de.testjens.view.screens.ScreenJoinGameHandler
import de.testjens.view.screens.ScreenStartHandler
import de.testjens.viewmodel.GameViewModel
import de.testjens.viewmodel.JoinGameViewModel

sealed class NavScreen(val route : String){
    object Start : NavScreen("start")
    object JoinGame : NavScreen("joingame")

    object Game: NavScreen("game/{gameID}"){
        fun gameId(gameID : String) = "game/{$gameID}"
    }
}

@Composable
fun AppNavigation(
    joinGameViewModel: JoinGameViewModel,
    gameViewModel: GameViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Start.route){
        composable(
            route = NavScreen.Start.route
        ){
            ScreenStartHandler(
                viewModel = joinGameViewModel,
                clickNewGame = {navController.navigate(NavScreen.JoinGame.route)},
                clickJoinGame = {navController.navigate(NavScreen.JoinGame.route)}
            )
        }
        composable(
            route = NavScreen.JoinGame.route
        ){
            ScreenJoinGameHandler(
                viewModel = joinGameViewModel,
                clickJoinGame = {id : String -> navController.navigate(NavScreen.Game.gameId(id))}
            )
        }
        composable(
            route= "game/{gameID}",
            arguments = listOf(
                navArgument("gameID"){
                    type = NavType.StringType
                }
            )
        ){
            val gameID = it.arguments?.getString("gameID")!!
            ScreenGameHandler(
                viewModel = gameViewModel.withGame(joinGameViewModel.getGameByID(gameID))
            )
        }
    }
}