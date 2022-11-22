package de.testjens.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.testjens.ui.theme.MoCoWareTheme
import de.testjens.view.ButtonChooseGame
import de.testjens.view.ButtonNewScreen
import de.testjens.viewmodel.AppViewModel


@Composable
fun ScreenStart(
    navController: NavController,
    viewModel: AppViewModel
){
//    MoCoWareTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ButtonNewScreen(
                text = "New Game",
                route = "joinGame",
                viewModel = viewModel,
                navController = navController
            )
            ButtonNewScreen(
                text = "Join Game",
                route = "joinGame",
                viewModel = viewModel,
                navController = navController
            )
        }
//    }

}