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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testrobert.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import de.mocoware.MainActivity
import de.mocoware.view.screens.ScreenGameHandler
import de.mocoware.view.screens.ScreenJoinGameHandler
import de.mocoware.view.screens.ScreenSplash
import de.mocoware.view.screens.ScreenStartHandler
import de.mocoware.viewmodel.GameViewModel
import de.mocoware.viewmodel.JoinGameViewModel


sealed class NavScreen(val route : String){
    object Splash:NavScreen("splash")
    object Start : NavScreen("start")
    object JoinGame : NavScreen("joingame")

    object Game: NavScreen("game/{gameID}"){
        fun gameId(gameID : String) = "game/{$gameID}"
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppNavigation(
    joinGameViewModel: JoinGameViewModel,
    gameViewModel: GameViewModel,
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
                    clickNewGame = { navController.navigate(NavScreen.JoinGame.route) },
                    clickJoinGame = { navController.navigate(NavScreen.JoinGame.route) }
                )
            }
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
                viewModel = gameViewModel.withGame(joinGameViewModel.getGameByID(gameID)),
                serivceSystem = service
            )
        }
    }
}