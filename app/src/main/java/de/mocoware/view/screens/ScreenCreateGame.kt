package de.mocoware.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.mocoware.viewmodel.JoinGameViewModel


@Composable
fun ScreenCreateGameHandler(
    viewModel: JoinGameViewModel
){
    ScreenCreateGameRender(

    )
}

@Composable
fun ScreenCreateGameRender(
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

    }
}