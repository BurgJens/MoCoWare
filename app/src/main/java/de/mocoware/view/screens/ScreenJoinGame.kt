package de.mocoware.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import de.mocoware.model.AvailableGame
import de.mocoware.view.elements.ButtonChooseGame
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.view.elements.TextFieldStandard
import de.mocoware.viewmodel.JoinGameViewModel
import kotlin.random.Random

@Composable
fun ScreenJoinGameHandler(
    viewModel: JoinGameViewModel,
    clickJoinGame: (String) -> Unit,
){
    val availableGames by viewModel.availableGames.observeAsState()

    ScreenJoinGameRender(
        availableGames = availableGames,
        clickJoinGame = {
            if(viewModel.getGameID() != "")clickJoinGame(viewModel.getGameID())
        },
        clickSelectGame = {gameID: String -> viewModel.setGameID(gameID)},
        test = {viewModel.addGame(Random.nextInt(20).toString())}
    )
}

@Composable
fun ScreenJoinGameRender(
    clickJoinGame: () -> Unit,
    clickSelectGame: (String) -> Unit,
    availableGames: List<AvailableGame>?,
    test: () -> Unit,
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
                                onClick = {clickSelectGame(it.gameID)}
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
                onClick = clickJoinGame,
                text = "Join Game",
                modifier = Modifier
            )
            ButtonStandard(
                onClick = test,
                text = "Create Game",
                modifier = Modifier
            )
        }
}


