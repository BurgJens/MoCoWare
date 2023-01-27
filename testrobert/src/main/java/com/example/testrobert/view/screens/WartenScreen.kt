package com.example.testrobert.view.screens

import android.content.Context
import android.content.Intent
import android.os.Looper
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
import java.util.logging.Handler

@Composable
fun WartenScreen(
    viewModel: SpielViewModel,
    navController: NavController,
    context: Context
    ){



    if (viewModel.spielIstAktiv.value&& viewModel.timer.value!! ==0) {

        context.stopService(Intent(context,LightSensor::class.java))
        context.stopService(Intent(context,Accelerometer::class.java))
        context.stopService(Intent(context,SpeedSensor::class.java))

        viewModel.speedSensorAktiv.value=false
        viewModel.lichtSensorAktiv.value=false
        viewModel.accelSensorAktiv.value=false

        viewModel.resetGames()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        viewModel.setTime(30)
        BackHandler { navController.navigate(NavRoutes.Warten.route) }

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            if (!viewModel.spielIstAktiv.value) {
                viewModel.spielIstAktiv.value=true
                navController.navigate(NavRoutes.GameScreen.route)
                viewModel.randomGame()
            }
        }, 3000)

        Text(modifier = Modifier.padding(20.dp), text ="Auf Mitspieler warten ...")
    }

}
