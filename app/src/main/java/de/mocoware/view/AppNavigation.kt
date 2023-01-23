package de.mocoware.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

@OptIn(ExperimentalPermissionsApi::class)
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
                    clickNewGame = { navController.navigate(NavScreen.JoinGame.route) },
                    clickJoinGame = { navController.navigate(NavScreen.JoinGame.route) }
                )
            }
        }
        {
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