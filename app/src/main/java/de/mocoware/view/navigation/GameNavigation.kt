package de.mocoware.view.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.model.minigames.DataMGconfusingButtons
import de.mocoware.view.screens.ScreenLobby
import de.mocoware.view.screens.minigames.*
import de.mocoware.viewmodel.GameViewModel

sealed class NavMG(val route : String){
    object Lobby : NavMG("lobby")

    object MGconfusingButtons : NavMG("confusingButtons")
    object MGannoyingButton : NavMG("annoyingButtons")
    object MGlaufenWithSerivce : NavMG("laufenWithService")
    object MGshake : NavMG("shake")
    object MGbeleuchtung : NavMG("beleuchtung")
    object highScore : NavMG("highScore")
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
                startGame = {navController.navigate(viewModel.routeToMG)},
                context = context
            )
        }
        composable(
            route = NavMG.highScore.route
        ) {
            ScreenScore(
                viewModel=viewModel,
                context = context
            )
        }
        composable(
            route = NavMG.MGannoyingButton.route
        ) {
            ScreenMGannoyingButtons(
                viewModel,
                viewModel.gameDatMGannoyingButtons,  // <- lieber gesammte data class?
                {navController.navigate(viewModel.routeToMG)}
            )
        }
        composable(
            route = NavMG.MGconfusingButtons.route
        ) {
            ScreenMGconfusingColorButtons(
                viewModel,
                viewModel.gameDatMGconfusingButtons,
                {navController.navigate(viewModel.routeToMG)}
            )
        }
        composable(
            route = NavMG.MGlaufenWithSerivce.route
        ){
            ScreeenMGlaufenWithService(
                viewModel = viewModel,
                context = context,
                {navController.navigate(viewModel.routeToMG)}
            )

        }
        composable(
            route = NavMG.MGshake.route
        ){
            ScreenMGshake(
                viewModel = viewModel,
                context = context,
                {navController.navigate(viewModel.routeToMG)}

            )

        }
        composable(
            route = NavMG.MGbeleuchtung.route
        ){
            ScreenMGbeleuchtung(
                viewModel = viewModel,
                context = context,
                {navController.navigate(viewModel.routeToMG)}

            )

        }
    }
}
