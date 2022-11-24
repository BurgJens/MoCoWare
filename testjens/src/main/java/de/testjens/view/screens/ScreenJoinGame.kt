package de.testjens.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.testjens.view.ButtonStandard
import de.testjens.view.elements.TextFieldStandard
import de.testjens.viewmodel.AppViewModel

@Composable
fun ScreenJoinGameHandler(
    viewModel: AppViewModel,
    clickJoinGame: () -> Unit
){
    ScreenJoinGameRender(
        clickJoinGame = clickJoinGame
    )
}

@Composable
fun ScreenJoinGameRender(
    clickJoinGame: () -> Unit
)
{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .border(2.dp, MaterialTheme.colors.background)
                    .padding(3.dp)
//                    .fillMaxSize()
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
//                for (each in viewModel.availableGames.gameList){
//                    ButtonChooseGame(
//                        text = each.name,
//                        modifier = Modifier
//                    )
//                }
            }
            TextFieldStandard("Filter")
            ButtonStandard(
                onClick = {clickJoinGame()
                          Log.d("Button", "Was clicked")
                          },
                text = "Join Game",
                modifier = Modifier
            )
        }
}