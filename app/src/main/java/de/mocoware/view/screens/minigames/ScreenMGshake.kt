package de.mocoware.view.screens.minigames

import android.content.Context
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
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.Shake
import de.mocoware.sensor.Accelerometer
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel
import kotlin.math.roundToInt

@Composable
fun ScreenMGshake(
    viewModel : GameViewModel,
    context: Context,
    navigate : () -> Unit
){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        var eins=0

        val test = viewModel.gameDatMGshake
        val accelObserve by viewModel.accel.observeAsState()
        val aa = remember { mutableStateOf(true) }
        val acc = remember { mutableStateOf(false) }




        if (!acc.value){
            context.startService(Intent(context,Accelerometer::class.java))
            acc.value=true
        }

        if(accelObserve?.get(0)!! >10 && aa.value){
            viewModel.serviceAccelIstAktiv=false
            aa.value=false
            acc.value=false
            viewModel.finishGame({ navigate() }, true)
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.padding(20.dp), text ="${test.text}", color = Color.Black)
            Text(modifier = Modifier.padding(20.dp), text ="Wert: ${accelObserve?.get(0)?.roundToInt()}", color = Color.Red)

        }






        MiniGameTimerComposable(
            viewModel,
            {
                viewModel.finishGame({ navigate() })

            }
        )
    }
}