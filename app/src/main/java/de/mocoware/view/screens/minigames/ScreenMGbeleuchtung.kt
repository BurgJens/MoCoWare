package de.mocoware.view.screens.minigames

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.Beleuchtung
import de.mocoware.model.minigames.Shake
import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.LightSensor
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel
import kotlin.math.roundToInt


@Composable
fun ScreenMGbeleuchtung(
    viewModel : GameViewModel,
    context: Context,
    navigate : () -> Unit
){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        var test = viewModel.gameDatMGbeleuchtung
        val beleuchtugObserve by viewModel.light.observeAsState()

        if (!viewModel.serviceLightIstAktiv){
            context.startService(Intent(context, LightSensor::class.java))
            viewModel.serviceLightIstAktiv=true
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.padding(20.dp), text ="${test.text}", color = Color.Black)
            Text(modifier = Modifier.padding(20.dp), text ="Licht Wert: ${beleuchtugObserve?.roundToInt()}", color = Color.Red)

        }

        MiniGameTimerComposable(
            viewModel,
            {
                viewModel.finishGame({ navigate() })
            }
        )
    }
}