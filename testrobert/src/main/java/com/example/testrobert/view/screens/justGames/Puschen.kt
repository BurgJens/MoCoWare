package com.example.testrobert.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.sql.Time
import java.time.LocalDateTime


@Composable
fun Puschen(
    i:MutableState<Int>
){


    val i = remember { i}

    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(modifier = Modifier
            .padding(20.dp),
            text = "Drücke den Button 30 mal")

        Text(modifier = Modifier
            .padding(20.dp),
            text = "${i.value}", color = Color.Red)


        if (i.value<30){
            Button(
                modifier = Modifier
                    .padding(20.dp)
                ,
                onClick = {
                    if (i.value<30) i.value +=1



                }) {
                Text(text = "Button")
            }
        }

    }

}