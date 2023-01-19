package de.mocoware.view.screens.minigames

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import de.mocoware.model.minigames.PushButtonsAway
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
    gameData : PushButtonsAway?,
    update : () -> Unit
){
    println("?")
    if (gameData != null) {
        Box {
            ScreenPushButtonsAwayRender(
                gameData.buttonList,
                update
            )
        }
    }
}