package com.example.testrobert.view.screens

import android.os.strictmode.UnbufferedIoViolation
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.testrobert.NavRoutes
import com.example.testrobert.model.Spiel
import com.example.testrobert.viewmodel.SpielViewModel


@Composable
fun GameScreen(
    viewModel: SpielViewModel,
    spiel: Spiel,
    navController: NavController,
    startServiceSpeed:()->Unit,
    startServiceAcce:()->Unit,
){

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
        println("test")
        startServiceAcce()
        viewModel.accelSensorAktiv=true
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
    Text(modifier = Modifier.padding(20.dp), text ="${viewModel.iPuschen.value}", color = Color.Red)
    Button(onClick = {

        if (viewModel.iPuschen.value==29) {
            navController.navigate(NavRoutes.Warten.route)
            viewModel.iPuschen.value = 0


        }

        viewModel.iPuschen.value += 1
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

    if (!viewModel.speedSensorAktiv) {
        println("test")
        startServiceSpeed()
        viewModel.speedSensorAktiv=true
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