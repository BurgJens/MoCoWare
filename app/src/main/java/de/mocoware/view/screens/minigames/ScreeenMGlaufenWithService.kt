package de.mocoware.view.screens.minigames

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.mocoware.model.minigames.LaufenWithService
import de.mocoware.model.minigames.MGlaufenWithService
import de.mocoware.viewmodel.GameViewModel


@Composable
fun ScreeenMGlaufenWithService(viewModel: GameViewModel,
                               navController: NavController,
                               context: Context,
                               ){

    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var test = viewModel.currentMG.gameData.data as LaufenWithService

    Text(modifier = Modifier.padding(20.dp), text ="${test.testAufgabe}", color = Color.Black)

    }
}