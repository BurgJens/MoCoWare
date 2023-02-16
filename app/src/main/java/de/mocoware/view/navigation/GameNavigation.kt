package de.mocoware.view.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mocoware.view.elements.MiniGameStartTimerComposable
import de.mocoware.view.screens.ScreenLobby
import de.mocoware.view.screens.minigames.*
import de.mocoware.viewmodel.GameViewModel

sealed class NavMG(val route : String){
    object Lobby : NavMG("lobby")
    object ScoreScreen : NavMG("highScore")
    object CountDown : NavMG("countDown")

    object MGconfusingButtons : NavMG("confusingButtons")
    object MGannoyingButton : NavMG("annoyingButtons")
    object MGlaufenWithSerivce : NavMG("laufenWithService")
    object MGshake : NavMG("shake")
    object MGbeleuchtung : NavMG("beleuchtung")

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
                startGame = {navController.navigate(NavMG.CountDown.route)},
                context = context
            )
        }
        composable(
            route = NavMG.ScoreScreen.route
        ) {
            ScreenScore(
                viewModel=viewModel,
                context = context
            )
        }
        composable(
            route = NavMG.CountDown.route
        ) {
            MiniGameStartTimerComposable(
                viewModel=viewModel,
                {navController.navigate(viewModel.routeToMG)}
            )
        }
        composable(
            route = NavMG.MGannoyingButton.route
        ) {
            ScreenMGannoyingButtons(
                viewModel,
                viewModel.gameDatMGannoyingButtons,  // <- lieber gesammte data class?
                {navController.navigate(NavMG.CountDown.route)}
            )
        }
        composable(
            route = NavMG.MGconfusingButtons.route
        ) {
            ScreenMGconfusingColorButtons(
                viewModel,
                viewModel.gameDatMGconfusingButtons,
                {navController.navigate(NavMG.CountDown.route)}
            )
        }
        composable(
            route = NavMG.MGlaufenWithSerivce.route
        ){
            ScreeenMGlaufenWithService(
                viewModel = viewModel,
                context = context,
                {navController.navigate(NavMG.CountDown.route)}
            )

        }
        composable(
            route = NavMG.MGshake.route
        ){
            ScreenMGshake(
                viewModel = viewModel,
                context = context,
                {navController.navigate(NavMG.CountDown.route)}

            )

        }
        composable(
            route = NavMG.MGbeleuchtung.route
        ){
            ScreenMGbeleuchtung(
                viewModel = viewModel,
                context = context,
                {navController.navigate(NavMG.CountDown.route)}

            )

        }
    }
}
