package de.testjens.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.testjens.ui.theme.MoCoWareTheme
import de.testjens.view.ButtonChooseGame
import de.testjens.view.ButtonStandard

@Composable
fun ScreenStart(
){
    MoCoWareTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ButtonStandard(text = "New Game")
            ButtonChooseGame(text = "Join Game")
        }
    }

}