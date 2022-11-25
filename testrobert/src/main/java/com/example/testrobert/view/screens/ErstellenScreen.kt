package com.example.testrobert.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testrobert.view.elements.ElementListe

@Composable
fun ErstellenScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 200.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(

            ) {
                items(10) { index ->

                }
            }

        }

    }
}

