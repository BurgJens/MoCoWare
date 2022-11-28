package com.example.testrobert.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
    navController: NavController
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
        if (spiel.name=="Laufen") SpielLaufen(viewModel = viewModel, navController =navController)

    }


}

@Composable
fun SpielDruecken(
    viewModel: SpielViewModel,
    navController:NavController
){
    Text(modifier = Modifier.padding(20.dp), text ="${viewModel.iPuschen.value}", color = Color.Red)
    Button(onClick = {

        if (viewModel.iPuschen.value==29){
            navController.navigate(NavRoutes.Warten.route)
            viewModel.iPuschen.value=0
        }

        viewModel.iPuschen.value += 1
    }) {
        Text(text = "Button")
    }
}


@Composable
fun SpielLaufen(
    viewModel: SpielViewModel,
    navController:NavController
){
    Text(modifier = Modifier.padding(20.dp), text ="${viewModel.iPuschen.value}", color = Color.Red)

}