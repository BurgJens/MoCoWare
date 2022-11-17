package de.testjens.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.testjens.ui.theme.MoCoWareTheme
import de.testjens.view.ButtonChooseGame
import de.testjens.view.ButtonStandard
import de.testjens.view.elements.TextFieldStandard

@Composable
fun ScreenBeitreten(
    games: MutableList<String>
)
{
    MoCoWareTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .border(2.dp, MaterialTheme.colors.background)
                    .padding(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                for (each in games){
                    ButtonChooseGame(text = each)
                }
            }
            TextFieldStandard("Filter")
            ButtonStandard(text = "Beitreten")
        }
    }
}