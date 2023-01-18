package com.example.testrobert.view.screens

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.strictmode.UnbufferedIoViolation
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.testrobert.MainActivity
import com.example.testrobert.NavRoutes

import com.example.testrobert.model.Spiel
import com.example.testrobert.viewmodel.SpielViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GameScreen(
    viewModel: SpielViewModel,
    spiel: Spiel,
    navController: NavController,
    startServiceSpeed:()->Unit,
    startServiceAcce:()->Unit,
    startServiceLight:()->Unit,
){

    val context = LocalContext.current


    val timer by viewModel?.timer.observeAsState()

    Text(modifier = Modifier.padding(20.dp), text ="Verbleibende Zeit: ${timer} Sekunden", color = Color.Red)


    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {


        Text(modifier = Modifier.padding(20.dp), text =spiel.aufagbeText)

        if (spiel.name=="Druecken") SpielDruecken(viewModel = viewModel, navController = navController)
        if (spiel.name=="Laufen") SpielLaufen(viewModel = viewModel, navController =navController,startServiceSpeed)
        if (spiel.name=="Shake") SpielShake(viewModel = viewModel, navController =navController,startServiceAcce)
        if (spiel.name=="Licht") SpielShake(viewModel = viewModel, navController =navController,startServiceLight)

    }


}

@Composable
fun SpielShake(
    viewModel: SpielViewModel,
    navController: NavController,
    startServiceAcce:()->Unit,

){

    val speedObserve by viewModel.accel.observeAsState()


    if (!viewModel.accelSensorAktiv) {
        viewModel.countDownTimer.start()
        println("test")
        startServiceAcce()
        viewModel.spielIstAktiv=true
        viewModel.accelSensorAktiv=true
    }

    if (viewModel.timer.value==0 && viewModel.spielIstAktiv){
        viewModel.countDownTimer.cancel()
        viewModel.spielIstAktiv=false
        navController.navigate(NavRoutes.Warten.route)
    }



    Text(modifier = Modifier.padding(20.dp), text ="max X Wert: ${speedObserve?.get(0)}", color = Color.Red)
    Text(modifier = Modifier.padding(20.dp), text ="max Y Wert: ${speedObserve?.get(1)}", color = Color.Red)
    Text(modifier = Modifier.padding(20.dp), text ="max Z Wert: ${speedObserve?.get(2)}", color = Color.Red)


}

@Composable
fun SpielDruecken(
    viewModel: SpielViewModel,
    navController:NavController,
){

    if(!viewModel.spielIstAktiv) {
        viewModel.spielIstAktiv=true
        viewModel.countDownTimer.start() }

    if (viewModel.timer.value==0 && viewModel.spielIstAktiv){
        viewModel.countDownTimer.cancel()
        viewModel.spielIstAktiv=false
        navController.navigate(NavRoutes.Warten.route) }


    Text(modifier = Modifier.padding(20.dp), text ="${viewModel.iPuschen.value}", color = Color.Red)
    Button(onClick = {

        viewModel.iPuschen.value += 1

        if (viewModel.iPuschen.value==30) {
            viewModel.iPuschen.value = 0

            println("Die benötigte Zeit: ${30- viewModel.timer.value!!}")

            viewModel.countDownTimer.cancel()
            navController.navigate(NavRoutes.Warten.route)
        }


    }) {
        Text(text = "Button")
    }
}


@Composable
fun SpielLaufen(
    viewModel: SpielViewModel,
    navController:NavController,
    startServiceSpeed:()->Unit,

){

    val timer by viewModel?.timer.observeAsState()

    if (!viewModel.speedSensorAktiv) {
        viewModel.countDownTimer.start()
        startServiceSpeed()
        viewModel.spielIstAktiv=true
        viewModel.speedSensorAktiv=true
    }

    if (timer==0 && viewModel.spielIstAktiv){
        viewModel.spielIstAktiv=false
        viewModel.countDownTimer.cancel()
        navController.navigate(NavRoutes.Warten.route)
    }

    val speedObserve by viewModel.speed.observeAsState()

    if (viewModel.speed.value!! > 14.0 && viewModel.speed!=null){
        viewModel.breack()

        navController.navigate(NavRoutes.Warten.route){
            this.popUpTo(NavRoutes.Warten.route) {
                this.inclusive = true
            }
        }
    }

    Text(modifier = Modifier.padding(20.dp), text ="${speedObserve}", color = Color.Red)



}