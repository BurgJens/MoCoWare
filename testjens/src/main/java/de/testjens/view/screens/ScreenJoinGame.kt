package de.testjens.view.screens

import android.util.Log
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import de.testjens.model.Game
import de.testjens.model.GameList
import de.testjens.view.ButtonChooseGame
import de.testjens.view.ButtonStandard
import de.testjens.view.elements.TextFieldStandard
import de.testjens.viewmodel.JoinGameViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import de.testjens.model.AvailableGame

@Composable
fun ScreenJoinGameHandler(
    viewModel: JoinGameViewModel,
    clickJoinGame: () -> Unit
){
    val availableGames by viewModel.availableGames.observeAsState()

    ScreenJoinGameRender(
        clickJoinGame = clickJoinGame,
        availableGames = availableGames,
    )
}

@Composable
fun ScreenJoinGameRender(
    clickJoinGame: () -> Unit,
    availableGames: List<AvailableGame>?,
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
                    .padding(3.dp),
//                    .fillMaxSize()
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    availableGames?.let{
                        items(availableGames){
                            ButtonChooseGame(
                                text = it.name,
                                modifier = Modifier,
                            )
                        }
                    } ?: run{
                            item {
                                Text(text = "no games found")
                        }
                    }
                }
            }
            TextFieldStandard("Filter")
            ButtonStandard(
                onClick = {clickJoinGame()},
                text = "Join Game",
                modifier = Modifier
            )
        }
}


