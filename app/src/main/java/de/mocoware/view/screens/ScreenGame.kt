package de.mocoware.view.screens

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
import androidx.compose.ui.tooling.preview.Preview
import de.mocoware.MainActivity
import de.mocoware.viewmodel.GameViewModel


@Composable
fun ScreenGameHandler(
    viewModel: GameViewModel,
    serivceSystem: MainActivity.SerivceSystem
){
    val game by viewModel.liveGame.observeAsState()

    ScreenGameRender(
        gameID = game!!.getID(),
        gameName = game!!.getName(),
        serivceSystem
    )
}


@Composable
fun ScreenGameRender(
    gameID: String,
    gameName: String,
    serivceSystem: MainActivity.SerivceSystem
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        serivceSystem.startSpeed()
        Text(text = gameName)
        Text(text = gameID)
    }
}