package de.mocoware.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.viewmodel.JoinGameViewModel


@Composable
fun ScreenStartHandler(
    viewModel: JoinGameViewModel,
    navigateNewGame: () -> Unit,
    navigateJoinGame: () -> Unit,
    navigateTest: () -> Unit
){
    ScreenStartRender(
        navigateNewGame = navigateNewGame,
        navigateJoinGame = navigateJoinGame,
        navigateTest = navigateTest
    )
}

@Composable
fun ScreenStartRender(
    navigateNewGame: () -> Unit,
    navigateJoinGame: () -> Unit,
    navigateTest: () -> Unit
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
                modifier = Modifier.padding(20.dp),             // Abstand auf 20 (+20) dp ! finde ich besser und ihr ?
                onClick = navigateNewGame
            )
            ButtonStandard(
                text = "Join Game",
                modifier = Modifier.padding(20.dp),
                onClick = {navigateJoinGame()
                    Log.d("Button", "Was clicked")
                }
            )
            ButtonStandard(
                text = "Test",
                modifier = Modifier,
                onClick = {navigateTest()
                    Log.d("Button", "Was clicked")
                }
            )
        }
}