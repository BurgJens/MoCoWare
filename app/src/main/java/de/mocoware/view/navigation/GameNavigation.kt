package de.mocoware.view.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.view.screens.ScreenLobby
import de.mocoware.view.screens.minigames.ScreeenMGlaufenWithService
import de.mocoware.view.screens.minigames.ScreenMGannoyingButtons
import de.mocoware.viewmodel.GameViewModel

sealed class NavMG(val route : String){
    object Lobby : NavMG("lobby")

    object MGconfusingButtons : NavMG("confusingButtons")
    object MGannoyingButton : NavMG("annoyingButtons")
    object MGlaufenWithSerivce : NavMG("laufenWithService")
}

@Composable
fun GameNavigation(
    viewModel: GameViewModel,
    context: Context
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavMG.Lobby.route) {
        composable(
            route = NavMG.Lobby.route
        ) {
            ScreenLobby(
                startGame = {navController.navigate(viewModel.routeToMG)}
            )
        }
        composable(
            route = NavMG.MGannoyingButton.route
        ) {
            println("__________________________________________MGannoyingButtons")
            ScreenMGannoyingButtons(
                viewModel,
                viewModel.currentGameData as DataMGannoyingButtons,
                {navController.navigate(viewModel.routeToMG)}
            )
        }
        composable(
            route = NavMG.MGlaufenWithSerivce.route
        ){
            ScreeenMGlaufenWithService(
                viewModel = viewModel,
                navController = navController,
                context = context
            )

        }
    }
}
