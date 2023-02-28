package de.mocoware.view.screens.minigames

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.mocoware.sensor.SpeedSensor
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel


@Composable
fun ScreeenMGlaufenWithService(
    viewModel : GameViewModel,
    navigate : () -> Unit
){
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        var test = viewModel.gameDataMGlaufenWithService
        val speedObserve by viewModel.speed.observeAsState()

        if (!viewModel.serviceSpeedIstAktiv){
            context.startService(Intent(context, SpeedSensor::class.java))
            viewModel.serviceSpeedIstAktiv=true
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.padding(20.dp), text ="${test.text}", color = Color.Black)
            Text(modifier = Modifier.padding(20.dp), text ="Geschwindigkeit: ${speedObserve}", color = Color.Red)

        }

        MiniGameTimerComposable(
            viewModel,
            {
                viewModel.finishGame(context,{ navigate() })
            }
        )
    }
}