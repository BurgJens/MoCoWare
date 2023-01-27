package com.example.testrobert.view.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
import androidx.navigation.NavController
import com.example.testrobert.NavRoutes
import com.example.testrobert.sensor.Accelerometer
import com.example.testrobert.sensor.LightSensor
import com.example.testrobert.sensor.SpeedSensor

import com.example.testrobert.viewmodel.SpielViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GameScreen(
    viewModel: SpielViewModel,
    navController: NavController,
    context: Context,
){

    val serviceStatus = remember { mutableStateOf(false) }
    val buttonValue = remember { mutableStateOf("Start Service") }

    val timer by viewModel.timer.observeAsState()



    Text(modifier = Modifier.padding(20.dp), text ="Verbleibende Zeit: ${timer} Sekunden", color = Color.Red)


    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {


        Text(modifier = Modifier.padding(20.dp), text =viewModel.spiel1.value.aufagbeText)

        if (viewModel.spiel1.value.name=="Druecken") SpielDruecken(viewModel = viewModel, navController = navController)
        if (viewModel.spiel1.value.name=="Laufen") SpielLaufen(viewModel = viewModel, navController =navController,context)
        if (viewModel.spiel1.value.name=="Shake") SpielShake(viewModel = viewModel, navController =navController,context)
        if (viewModel.spiel1.value.name=="Licht") SpielLight(viewModel = viewModel, navController =navController,context)

    }


}

@Composable
fun SpielShake(
    viewModel: SpielViewModel,
    navController: NavController,
    context: Context,

){
    val accelObserve by viewModel.accel.observeAsState()

    if (!viewModel.accelSensorAktiv.value && viewModel.timer.value!! >20) {
        println("test")
        context.startService(Intent(context, Accelerometer::class.java))

        viewModel.spielIstAktiv.value=true
        viewModel.accelSensorAktiv.value=true
    }

    if (viewModel.timer.value!! <=0 && viewModel.spielIstAktiv.value){
        navController.navigate(NavRoutes.Warten.route)
    }



    Text(modifier = Modifier.padding(20.dp), text ="max X Wert: ${accelObserve?.get(0)}", color = Color.Red)
    Text(modifier = Modifier.padding(20.dp), text ="max Y Wert: ${accelObserve?.get(1)}", color = Color.Red)
    Text(modifier = Modifier.padding(20.dp), text ="max Z Wert: ${accelObserve?.get(2)}", color = Color.Red)


}

@Composable
fun SpielLight(
    viewModel: SpielViewModel,
    navController: NavController,
    context: Context,
    ){
    val lightObserve by viewModel.light.observeAsState()

    if (!viewModel.lichtSensorAktiv.value && viewModel.timer.value!! >20) {
        context.startService(Intent(context, LightSensor::class.java))
        viewModel.spielIstAktiv.value=true
        viewModel.lichtSensorAktiv.value=true
    }

    if (viewModel.timer.value!! <=0 && viewModel.spielIstAktiv.value){
        navController.navigate(NavRoutes.Warten.route)
    }




    Text(modifier = Modifier.padding(20.dp), text ="Licht Wert: ${lightObserve?.toInt()}", color = Color.Red)



}

@Composable
fun SpielDruecken(
    viewModel: SpielViewModel,
    navController:NavController,
){

    val iPuschen = remember { mutableStateOf(0) }

    if(!viewModel.spielIstAktiv.value&& viewModel.timer.value!! >20) {
        viewModel.spielIstAktiv.value=true }

    if (viewModel.timer.value!! <=0 && viewModel.spielIstAktiv.value){
        navController.navigate(NavRoutes.Warten.route) }


    Text(modifier = Modifier.padding(20.dp), text ="${iPuschen.value}", color = Color.Red)
    Button(onClick = {

        iPuschen.value += 1

        if (iPuschen.value>=30) {
            iPuschen.value = 30

            println("Die benötigte Zeit: ${30- viewModel.timer.value!!}")


            if (viewModel.timer.value!! <=0 && viewModel.spielIstAktiv.value){
                navController.navigate(NavRoutes.Warten.route) }
        }


    }) {
        Text(text = "Button")
    }
}


@Composable
fun SpielLaufen(
    viewModel: SpielViewModel,
    navController:NavController,
    context: Context,

){

    if (!viewModel.speedSensorAktiv.value&& viewModel.timer.value!! >20) {
        context.startService(Intent(context, SpeedSensor::class.java))
        viewModel.spielIstAktiv.value=true
        viewModel.speedSensorAktiv.value=true
    }

    if (viewModel.timer.value!! <= 0 && viewModel.spielIstAktiv.value){
        navController.navigate(NavRoutes.Warten.route)
    }

    val speedObserve by viewModel.speed.observeAsState()

    if (viewModel.speed.value!! > 14.0 && viewModel.speed!=null){
        viewModel.resetGames()

        navController.navigate(NavRoutes.Warten.route){
            this.popUpTo(NavRoutes.Warten.route) {
                this.inclusive = true
            }
        }
    }

    Text(modifier = Modifier.padding(20.dp), text ="${speedObserve}", color = Color.Red)



}