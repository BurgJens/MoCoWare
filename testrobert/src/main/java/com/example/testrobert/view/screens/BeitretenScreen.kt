package com.example.testrobert.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testrobert.model.Spiel
import com.example.testrobert.model.SpielListe
import com.example.testrobert.view.elements.ElementListe


@Composable
fun BeitretenScreen(
    navController: NavController,
    itemsToShow: List<Spiel>,
    onItemClicked: (spiel:Spiel) -> Unit

){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 200.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    )
    {
        LazyColumn(
        ) {
            itemsIndexed(itemsToShow) { index, (name) ->
                ElementListe(name=name){
                    onItemClicked(itemsToShow[index])
                }
                Divider()
            }
        }
    }
}