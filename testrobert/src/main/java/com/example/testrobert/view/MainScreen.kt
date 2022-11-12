package com.example.testrobert


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*


@Composable
fun StartScreen(
    navController: NavController
) {
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
                    navController.navigate("Erstellen") // ändern wenn mehr screens erstellt

                }) {
                Text(text = "Erstellen")
            }
            Button(
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    navController.navigate("Erstellen") // ändern wenn mehr screens erstellt
                }) {
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
fun ErstellenScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 100.dp, 0.dp, 100.dp),
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


@Composable
fun BeitretenScreen(
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 100.dp, 0.dp, 100.dp),
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




