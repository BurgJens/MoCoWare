package de.mocoware.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mocoware.MainActivity
import de.mocoware.view.screens.ScreenCreateGameHandler
import de.mocoware.view.screens.ScreenGameHandler
import de.mocoware.view.screens.ScreenJoinGameHandler
import de.mocoware.view.screens.ScreenStartHandler
import de.mocoware.view.screens.minigames.ScreenGameTestHandler
import de.mocoware.viewmodel.CreateGameViewModel
import de.mocoware.viewmodel.GameViewModel
import de.mocoware.viewmodel.JoinGameViewModel
import de.mocoware.viewmodel.TestViewModel


sealed class NavScreen(val route : String){
    object Start : NavScreen("start")
    object JoinGame : NavScreen("joingame")
    object CreateGame : NavScreen("creategame")
    object Test : NavScreen("test")

    object Game: NavScreen("game")
//    object Game: NavScreen("game/{gameID}"){
//        fun gameId(gameID : String) = "game/{$gameID}"
//    }
}

@Composable
fun AppNavigation(
    joinGameViewModel: JoinGameViewModel,
    gameViewModel: GameViewModel,
    createGameViewModel: CreateGameViewModel,
    testViewModel: TestViewModel,
    service: MainActivity.SerivceSystem

){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Start.route){
        composable(
            route = NavScreen.Start.route
        ){
            ScreenStartHandler(
                viewModel = joinGameViewModel,
                navigateNewGame = {navController.navigate(NavScreen.CreateGame.route)},
                navigateJoinGame = {navController.navigate(NavScreen.JoinGame.route)},
                navigateTest = {navController.navigate(NavScreen.Test.route)}
            )
        }
        composable(
            route = NavScreen.CreateGame.route
        ){
            ScreenCreateGameHandler(
                viewModel = createGameViewModel,
                navigateGame = {navController.navigate(NavScreen.Game.route)}
            )
        }
        composable(
            route = NavScreen.JoinGame.route
        ){
            ScreenJoinGameHandler(
                viewModel = joinGameViewModel,
                navigateJoinGame = { id : String -> navController.navigate(NavScreen.Game.route)}
            )
        }
        composable(
            route = NavScreen.Game.route,
        ){
            ScreenGameHandler(
                viewModel = gameViewModel,
                serivceSystem = service
            )
        }



        composable(
            route = NavScreen.Test.route
        ){
            ScreenGameTestHandler(
                testViewModel
            )
        }
    }
}