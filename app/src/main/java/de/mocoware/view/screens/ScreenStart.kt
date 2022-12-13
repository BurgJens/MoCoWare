package de.mocoware.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.mocoware.view.ButtonStandard
import de.mocoware.viewmodel.JoinGameViewModel


@Composable
fun ScreenStartHandler(
    viewModel: JoinGameViewModel,
    clickNewGame: () -> Unit,
    clickJoinGame: () -> Unit
){
    ScreenStartRender(
        navigateNewGame = clickNewGame,
        navigateJoinGame = clickJoinGame
    )
}

@Composable
fun ScreenStartRender(
    navigateNewGame: () -> Unit,
    navigateJoinGame: () -> Unit
){
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
                onClick = navigateNewGame
            )
            ButtonStandard(
                text = "Join Game",
                modifier = Modifier,
                onClick = {navigateJoinGame()
                    Log.d("Button", "Was clicked")
                }
            )
        }
}