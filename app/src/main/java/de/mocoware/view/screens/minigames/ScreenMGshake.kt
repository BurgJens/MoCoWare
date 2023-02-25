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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.Shake
import de.mocoware.sensor.Accelerometer
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel
import kotlin.math.roundToInt

@Composable
fun ScreenMGshake(
    viewModel : GameViewModel,
    navigate : () -> Unit
){
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val test = viewModel.gameDatMGshake
        val accelObserve by viewModel.accel.observeAsState()

        if (!viewModel.serviceAccelIstAktiv){
            context.startService(Intent(context,Accelerometer::class.java))
            viewModel.serviceAccelIstAktiv=true
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.padding(20.dp), text ="${test.text}", color = Color.Black)
            Text(modifier = Modifier.padding(20.dp), text ="max X Wert: ${accelObserve?.get(0)?.roundToInt()}", color = Color.Red)
            Text(modifier = Modifier.padding(20.dp), text ="max Y Wert: ${accelObserve?.get(1)?.roundToInt()}", color = Color.Red)
            Text(modifier = Modifier.padding(20.dp), text ="max Z Wert: ${accelObserve?.get(2)?.roundToInt()}", color = Color.Red)
        }



        MiniGameTimerComposable(
            viewModel,
            {
                viewModel.finishGame({ navigate() })

            }
        )
    }
}