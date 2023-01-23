package de.mocoware.view

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testrobert.Permission
import de.mocoware.MainActivity
import de.mocoware.view.screens.ScreenCreateGameHandler
import de.mocoware.view.screens.ScreenGameHandler
import de.mocoware.view.screens.ScreenJoinGameHandler
import de.mocoware.view.screens.ScreenSplash
import de.mocoware.view.screens.ScreenStartHandler
import de.mocoware.view.screens.minigames.ScreenGameTestHandler
import de.mocoware.viewmodel.CreateGameViewModel
import de.mocoware.viewmodel.GameViewModel
import de.mocoware.viewmodel.JoinGameViewModel
import de.mocoware.viewmodel.TestViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


sealed class NavScreen(val route : String){
    object Splash:NavScreen("splash")
    object Start : NavScreen("start")
    object JoinGame : NavScreen("joingame")
    object CreateGame : NavScreen("creategame")
    object Test : NavScreen("test")

    object Game: NavScreen("game")
//    object Game: NavScreen("game/{gameID}"){
//        fun gameId(gameID : String) = "game/{$gameID}"
//    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AppNavigation(
    joinGameViewModel: JoinGameViewModel,
    gameViewModel: GameViewModel,
    createGameViewModel: CreateGameViewModel,
    testViewModel: TestViewModel,
    service: MainActivity.SerivceSystem

){
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = NavScreen.Splash.route){
        composable(
            route=NavScreen.Splash.route
        ){
            ScreenSplash(viewModel = gameViewModel, navController =navController )
        }
        composable(
            route = NavScreen.Start.route
        ) {
            Permission(
                permissionNotAvailableContent = {
                    Column(Modifier.fillMaxSize()) {
                        Text("O noes! No Camera!")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                context.startActivity(
                                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                        data = Uri.fromParts("package", context.packageName, null)
                                    }
                                )
                            }
                        ) {
                            Text("Öffne deine Einstellungen")
                        }
                    }
                }
            ) {
                ScreenStartHandler(
                    viewModel = joinGameViewModel,
                    navigateNewGame = { navController.navigate(NavScreen.CreateGame.route) },
                    navigateJoinGame = { navController.navigate(NavScreen.JoinGame.route) },
                    navigateTest = { navController.navigate(NavScreen.Test.route) }
                )
            }
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
                navigateJoinGame = {}
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