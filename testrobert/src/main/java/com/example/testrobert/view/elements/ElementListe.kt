package com.example.testrobert.view.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.net.IDN

@Composable
fun ElementListe(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable (onClick = onClick )
            .height(100.dp)
            .width(350.dp),
        contentAlignment = Alignment.Center,
    ) {

        Text(name, modifier = Modifier.align(Alignment.Center))

    }

}

