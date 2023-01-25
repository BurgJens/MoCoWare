package com.example.testalex.view.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonStandard(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun ButtonChooseGame(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
){
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.background
        ),
        border = BorderStroke(
            2.dp,
            MaterialTheme.colors.surface
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}