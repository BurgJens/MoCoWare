package com.example.testrobert.view.screens

import android.content.Context
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.example.testrobert.MainActivity
import com.example.testrobert.NavRoutes
import com.example.testrobert.model.Spiel
import com.example.testrobert.sensor.Accelerometer
import com.example.testrobert.sensor.LightSensor
import com.example.testrobert.sensor.SpeedSensor
import com.example.testrobert.viewmodel.SpielViewModel

@Composable
fun WartenScreen(
    viewModel: SpielViewModel,
    navController: NavController,
    context: Context
    ){

    if (viewModel.lichtSensorAktiv.value) {
        context.stopService(Intent(context,LightSensor::class.java))
        viewModel.lichtSensorAktiv.value=false
    }

    if (viewModel.speedSensorAktiv.value) {
        context.stopService(Intent(context,SpeedSensor::class.java))
        viewModel.speedSensorAktiv.value=false
    }
    if (viewModel.accelSensorAktiv.value) {
        context.stopService(Intent(context,Accelerometer::class.java))
        viewModel.maxXwet= 0.0F
        viewModel.maxYwet=0.0F
        viewModel.maxZwet=0.0F
        viewModel.accelSensorAktiv.value=false

    }

        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            viewModel.setTime(30)

            BackHandler {
                navController.navigate(NavRoutes.Warten.route)
            }

            Text(modifier = Modifier.padding(20.dp), text ="Auf Mitspieler warten ...")
        }

    }
