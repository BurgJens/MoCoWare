package de.mocoware.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.view.screens.minigames.ScreenMGannoyingButtons
import de.mocoware.viewmodel.GameViewModel

sealed class NavMG(val route : String){
    object MGannoyingButton : NavMG("annoyingButtons")
}

@Composable
fun GameNavigation(
    viewModel: GameViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavMG.MGannoyingButton.route) {
        composable(
            route = NavMG.MGannoyingButton.route
        ) {
            println("__________________________________________________NAVIGATED")
            viewModel.countDownTimer.start()
            ScreenMGannoyingButtons(
                viewModel.currentGameData as DataMGannoyingButtons,
                {
                    viewModel.finishGame()
                    navController.navigate(viewModel.routeToMG)
                },
                viewModel.timer
            )
        }
    }
}
