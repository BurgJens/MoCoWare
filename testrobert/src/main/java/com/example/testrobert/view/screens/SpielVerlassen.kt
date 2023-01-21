package com.example.testrobert.view.screens

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
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.testrobert.NavRoutes

@Composable
fun QuestionScreen(navController: NavController) {
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
                onClick ={navController.navigate(NavRoutes.Start.route)})  {
                Text("JA")
            }
            Button(modifier = Modifier
                .scale(1.2f)
                .width(120.dp),
                onClick = {navController.navigate(NavRoutes.GameScreen.route)}) {
                Text("NEIN!")
            }
        }

    }
}