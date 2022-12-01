package com.example.testrobert.view.screens

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
import com.example.testrobert.model.Spiel
import com.example.testrobert.viewmodel.SpielViewModel

@Composable
fun WartenScreen(
    viewModel: SpielViewModel,
    spiel: Spiel,
    navController: NavController,
    stopServiceSpeed:()->Unit,
    stopServiceAcce: () -> Unit
    ){

    if (viewModel.speedSensorAktiv) {
        stopServiceSpeed()
        viewModel.speedSensorAktiv=false
    }
    if (viewModel.accelSensorAktiv) {
        stopServiceAcce()
        viewModel.maxXwet= 0.0F
        viewModel.maxYwet=0.0F
        viewModel.maxZwet=0.0F
        viewModel.accelSensorAktiv=false

    }

        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            viewModel.setTime(30)



            Text(modifier = Modifier.padding(20.dp), text ="Auf Mitspieler warten ...")



        }

    }
