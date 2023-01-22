package com.example.testrobert.view.screens

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.testrobert.NavRoutes
import com.example.testrobert.sensor.Accelerometer
import com.example.testrobert.sensor.LightSensor
import com.example.testrobert.sensor.SpeedSensor
import com.example.testrobert.viewmodel.SpielViewModel

@Composable
fun QuestionScreen(navController: NavController,
                   viewModel: SpielViewModel,
                   context: Context ){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(modifier = Modifier
            .padding(20.dp,50.dp),
            text = "Willst du das Spiel wirklich verlassen?")


        Row(modifier = Modifier
            .padding(20.dp,80.dp))

        {
            Button(modifier = Modifier
                .scale(0.6f)
                .width(120.dp),
                onClick ={
                    navController.popBackStack()

                    context.stopService(Intent(context,SpeedSensor::class.java))
                    context.stopService(Intent(context,Accelerometer::class.java))
                    context.stopService(Intent(context,LightSensor::class.java))

                    viewModel.speedSensorAktiv.value=false
                    viewModel.spielIstAktiv.value=false
                    viewModel.accelSensorAktiv.value=false
                    navController.navigate(NavRoutes.Start.route)
                })  {
                Text("JA")

            }
            Button(modifier = Modifier
                .scale(1.2f)
                .width(120.dp),
                onClick = {
                    if (viewModel.timer.value==30){navController.navigate(NavRoutes.Warten.route)}
                    else{
                        navController.navigate(NavRoutes.GameScreen.route)
                    } }) {
                Text("NEIN!")
            }
        }

    }
}