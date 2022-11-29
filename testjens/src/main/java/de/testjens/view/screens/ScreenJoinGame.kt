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

@Composable
fun ScreenJoinGameHandler(
    viewModel: JoinGameViewModel,
    clickJoinGame: () -> Unit
){
    val availableGames by viewModel.availableGames.observeAsState()

    val test by viewModel.test.observeAsState()

//    val fs = availableGames.value

    ScreenJoinGameRender(
        clickJoinGame = clickJoinGame,
        availableGames = availableGames,
        testus = test
    )
}

val itemsList = (0..5).toList()

@Composable
fun ScreenJoinGameRender(
    clickJoinGame: () -> Unit,
    availableGames: List<Game>?,
    testus: Int?
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
                ButtonChooseGame(text = "Pimmel", modifier = Modifier)

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    availableGames?.let{
//                        for (game in availableGames){
//                            item {
//                                ButtonChooseGame(
//                                    text = game.name,
//                                    modifier = Modifier,
//                                )
//                            }
//                        }

                        items(availableGames){
                            ButtonChooseGame(
                                text = it.name,
                                modifier = Modifier,
                            )
                        }

                    } ?: run{
                        item {
                            ButtonChooseGame(
                                text = "pgiubdhasug",
                                modifier = Modifier,
                            )
                        }
                    }



                }

                ButtonChooseGame(text = "Pimmel", modifier = Modifier)
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


