package de.mocoware.view.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.mocoware.MainActivity
import de.mocoware.viewmodel.GameViewModel


@Composable
fun ScreenGameHandler(
    viewModel: GameViewModel,
    context: Context
){
    val game by viewModel.liveGame.observeAsState()

    Column() {
        Text(text = viewModel.getCurrentGameName())
        Text(text = viewModel.getCurrentGameID())
    }
//    ScreenGameRender(
//        gameID = viewModel.getCurrentGameID(),
//        gameName = viewModel.getCurrentGameName(),
//        viewModel,
//        serivceSystem
//    )
}


@Composable
fun ScreenGameRender(
    gameID: String,
    gameName: String,
    viewModel: GameViewModel,
){

    val timer by viewModel?.timer.observeAsState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = gameName)
        Text(text = gameID)
        Text(modifier = Modifier.padding(20.dp), text ="Verbleibende Zeit: ${timer} Sekunden", color = Color.Red)
    }
}