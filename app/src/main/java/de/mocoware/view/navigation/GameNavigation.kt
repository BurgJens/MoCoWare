package de.mocoware.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.view.screens.ScreenLobby
import de.mocoware.view.screens.minigames.ScreenMGannoyingButtons
import de.mocoware.viewmodel.GameViewModel

sealed class NavMG(val route : String){
    object Lobby : NavMG("lobby")

    object MGannoyingButton : NavMG("annoyingButtons")
}

@Composable
fun GameNavigation(
    viewModel: GameViewModel
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
            ScreenMGannoyingButtons(
                viewModel.currentGameData as DataMGannoyingButtons,
                {
                    viewModel.finishGame()
                    navController.navigate(viewModel.routeToMG)
                },
                viewModel.countDownTimer,
                viewModel.timer,
            )
        }
    }
}
