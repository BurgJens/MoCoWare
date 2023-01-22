package de.mocoware.view.screens.minigames

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.model.minigames.GameData
import de.mocoware.model.minigames.MGannoyingButtons
import de.mocoware.model.minigames.MiniGame
import de.mocoware.viewmodel.TestViewModel

@Composable
fun ScreenGameTestHandler(
    viewModel: TestViewModel
){
    val gameData by viewModel.gameDataLive.observeAsState()
    ScreenGameTestRender(
        gameData
    ) { viewModel.updateGamedata() }
}

@Composable
fun ScreenGameTestRender(
    gameData : MiniGame?,
    update : () -> Unit
){
    println("RENDER")

    Box {
        if (gameData != null) {
            when(gameData) {
                is MGannoyingButtons ->
                    ScreenMGannoyingButtons(
                        gameData,
                        update
                    )
            }
        }
    }
}