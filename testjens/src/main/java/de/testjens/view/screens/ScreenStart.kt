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
import de.testjens.view.ButtonStandard
import de.testjens.viewmodel.AppViewModel


@Composable
fun ScreenStart(
    navigateJoinGame: () -> Unit,
    navigateBeitreten: () -> Unit
){
//    MoCoWareTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ButtonStandard(
                text = "New Game",
                modifier = Modifier,
                onClick = navigateBeitreten
            )
            ButtonStandard(
                text = "Join Game",
                modifier = Modifier,
                onClick = navigateJoinGame
            )
        }
//    }

}