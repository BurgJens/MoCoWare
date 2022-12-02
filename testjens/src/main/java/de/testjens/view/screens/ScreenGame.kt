package de.testjens.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.testjens.view.NavScreen
import de.testjens.viewmodel.GameViewModel
import de.testjens.viewmodel.JoinGameViewModel

@Composable
fun ScreenGameHandler(
    viewModel: GameViewModel
){
    val game by viewModel.liveGame.observeAsState()

    ScreenGameRender(
        gameID = game!!.getID(),
        gameName = game!!.getName()
    )
}

@Composable
fun ScreenGameRender(
    gameID: String,
    gameName: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = gameName)
        Text(text = gameID)
    }
}