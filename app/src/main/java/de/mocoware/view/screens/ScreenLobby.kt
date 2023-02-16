package de.mocoware.view.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.Gyroskope
import de.mocoware.sensor.LightSensor
import de.mocoware.sensor.SpeedSensor
import de.mocoware.view.elements.ButtonStandard

@Composable
fun ScreenLobby(
    startGame: () -> Unit,
    context: Context
){
    context.stopService(Intent(context,Accelerometer::class.java))
    context.stopService(Intent(context,LightSensor::class.java))
    context.stopService(Intent(context,SpeedSensor::class.java))
    context.stopService(Intent(context,Gyroskope::class.java))
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ButtonStandard(text = "Start Game", modifier = Modifier, onClick = startGame)
    }
}