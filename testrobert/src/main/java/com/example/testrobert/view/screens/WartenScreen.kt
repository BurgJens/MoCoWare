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
    navController: NavController
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(modifier = Modifier.padding(20.dp), text ="Auf Mitspieler warten ...")



        }

    }
