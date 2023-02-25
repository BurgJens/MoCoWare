package de.mocoware.view.navigation

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
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
    object MGLoRButtonMasher : NavMG("loRButtonMasher")
}

@Composable
fun GameNavigation(
    viewModel: GameViewModel,
    navigateStart: () -> Unit
) {
    val navController = rememberNavController()
    val context = LocalContext.current as Activity

    NavHost(navController = navController, startDestination = NavMG.Lobby.route) {
        composable(
            route = NavMG.Lobby.route
        ) {
            ScreenLobby(
                startGame = {navController.navigate(NavMG.CountDown.route)}
            )
        }
        composable(
            route = NavMG.ScoreScreen.route
        ) {
            ScreenScore(
                viewModel=viewModel,
                navigateStart
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
            context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            ScreenMGannoyingButtons(
                viewModel,
                viewModel.gameDatMGannoyingButtons,  // <- lieber gesammte data class?
                {navController.navigate(NavMG.CountDown.route)}
            )
        }
        composable(
            route = NavMG.MGconfusingButtons.route
        ) {
            context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

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
                {navController.navigate(NavMG.CountDown.route)}
            )

        }
        composable(
            route = NavMG.MGshake.route
        ){
            ScreenMGshake(
                viewModel = viewModel,
                {navController.navigate(NavMG.CountDown.route)}

            )

        }
        composable(
            route = NavMG.MGbeleuchtung.route
        ){
            ScreenMGbeleuchtung(
                viewModel = viewModel,
                {navController.navigate(NavMG.CountDown.route)}

            )
        }
        composable(
            route = NavMG.MGLoRButtonMasher.route
        ) {
            context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            ScreenMGLoRButtonMasher(
                viewModel,
                viewModel.gameDatMGLoRButtonMasher,
                {navController.navigate(NavMG.CountDown.route)}
            )
        }
        composable(
            route = NavMG.MGLoRButtonMasher.route
        ) {
            context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            ScreenMGLoRButtonMasher(
                viewModel,
                viewModel.gameDatMGLoRButtonMasher,
                {navController.navigate(NavMG.CountDown.route)}
            )
        }
    }
}
