package com.example.testalex.view.screens.minigames

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.testalex.model.minigames.DataMGannoyingButtons
import com.example.testalex.model.minigames.GameData
import com.example.testalex.viewmodel.TestViewModel


@Composable
fun ScreenGameTestHandler(
    viewModel: TestViewModel
){
    val gameData by viewModel.gameDataLive.observeAsState()
    ScreenGameTestRender(
        gameData
    ) { viewModel.finishGame() }
}

@Composable
fun ScreenGameTestRender(
    gameData : GameData?,
    finishGame : () -> Unit
){
    println("                                    RERENDER")

    Box {
        if (gameData != null) {
            when(gameData) {
                is DataMGannoyingButtons ->
                    ScreenMGannoyingButtons(
                        gameData,
                        finishGame
                    )
            }
        }
    }
}