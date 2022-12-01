package de.testjens.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.testjens.model.Game
import de.testjens.view.screens.ScreenJoinGameHandler
import de.testjens.view.screens.ScreenStartHandler
import de.testjens.viewmodel.JoinGameViewModel
import kotlin.random.Random

sealed class NavScreen(val route : String){
    object Start : NavScreen("start")
    object JoinGame : NavScreen("joingame")

    object Game: NavScreen("game/{gameID}"){
        fun gameId(gameID : String) = "playr/$gameID"
    }
}

@Composable
fun AppNavigation(
    viewModel: JoinGameViewModel
){
    val navController = rememberNavController()

    var gameID = ""

    NavHost(navController = navController, startDestination = NavScreen.Start.route){
        composable(
            route = NavScreen.Start.route
        ){
            ScreenStartHandler(
                viewModel = viewModel,
                clickNewGame = {navController.navigate(NavScreen.JoinGame.route)},
                clickJoinGame = {navController.navigate(NavScreen.JoinGame.route)}
            )
        }
        composable(
            route = NavScreen.JoinGame.route
        ){
            ScreenJoinGameHandler(
                viewModel = viewModel,
                clickJoinGame = {setGameID("")},
                clickSelectGame = {navController.navigate(NavScreen.Game.gameId(gameID))}
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
            val gameID = it.arguments?.getString("userID")
        }
    }

    fun setGameID (newID : String){
        gameID = newID
    }
//
//    fun pathNextRoute(){
//        if(pathNext != ""){
//            navController.navigate(pathNext)
//        }
//        pathNext = ""
//    }
}