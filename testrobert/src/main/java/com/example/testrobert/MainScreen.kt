package com.example.testrobert

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.navigation.compose.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController






@Composable
fun StartScreen(
    navController: NavController
) {
    println("sdfgshjfk")
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column() {
            Button(
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    navController.navigate("Start")

                }) {
                Text(text = "Erstellen")
            }
            Button(
                modifier = Modifier
                    .padding(20.dp),
                onClick = { /*TODO*/ }) {
                Text(text = "Beitreten")
            }
        }
        // Box(){
        //     Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
        //     Text(text = "über dem Foro")
        // }

    }
}








@Composable
fun SpielerListe(){

    Box(modifier = Modifier
        .padding(0.dp,100.dp,0.dp,100.dp),
        contentAlignment = Alignment.Center
    )
    {
        LazyColumn(
        ) {
            items(350) { index ->
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(300.dp)
                        .padding(15.dp)
                        .background(Color.Black.copy(0.5f)),
                ) {
                }
            }
        }
    }
}



