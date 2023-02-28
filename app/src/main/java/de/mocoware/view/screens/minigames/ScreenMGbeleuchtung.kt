package de.mocoware.view.screens.minigames

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.mocoware.sensor.LightSensor
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel
import kotlin.math.roundToInt


@Composable
fun ScreenMGbeleuchtung(
    viewModel : GameViewModel,
    navigate : () -> Unit
){
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        var test = viewModel.gameDataMGbeleuchtung
        val beleuchtugObserve by viewModel.light.observeAsState()

        val aa = remember { mutableStateOf(true) }
        val lightB = remember { mutableStateOf(false) }

        if (!lightB.value){
            context.startService(Intent(context, LightSensor::class.java))
            lightB.value=true
        }

        if (beleuchtugObserve!! >500 && aa.value){
            aa.value=false
            lightB.value=false
            viewModel.finishGame(context,{ navigate() }, true)
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
                viewModel.finishGame(context,{ navigate()})
            }
        )
    }
}