package com.example.testalex.view.screens.minigames

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testalex.model.minigames.DataMGannoyingButtons
import com.example.testalex.viewmodel.TestViewModel

sealed class NavMG(val route : String){
    object MGannoyingButton : NavMG("annoyingButtons")
}

@Composable
fun MiniGameNavigation(
    viewModel: TestViewModel
) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = NavMG.MGannoyingButton.route) {
        composable(
            route = NavMG.MGannoyingButton.route
        ) {
            ScreenMGannoyingButtons(
                viewModel.currentGameData as DataMGannoyingButtons
            ) {
                viewModel.finishGame()
                navController.navigate(viewModel.navigateNext)
            }
        }
    }
}
