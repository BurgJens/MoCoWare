@file:Suppress("UNUSED_EXPRESSION")

package de.mocoware.view.screens.minigames

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.mocoware.model.minigames.DataMGLoRButtonMasher
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.view.elements.GenericButton
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel

@Composable
fun ScreenMGLoRButtonMasher(
    viewModel : GameViewModel,
    gameData : DataMGLoRButtonMasher,
    navigate : () -> Unit,
){

    val context = LocalContext.current as Activity
    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    var failed by remember { mutableStateOf(false) }

    var clickCount1 by remember { mutableStateOf(0) }

   Card(
        modifier = Modifier.fillMaxSize(),

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier,
            contentAlignment = Alignment.Center
            )
            {
                for (each in gameData.ButtonMasherList){
                    when(each.winButton){
                    false -> {
                        falscherButton(
                            offsetX = each.x,
                            text = "",
                            color = each.color,
                            onClick = { failed = true })
                    }

                    true -> {
                        richtigerButton(
                            offsetX = each.x,
                            text = "",
                            color = each.color,
                            onClick = { clickCount1++})
                            }
                    }

                }
            }
            if (clickCount1 == 20) {
                viewModel.finishGame()
            }

        }
       Row {
           Text(text = "Drück 20 mal auf Grün",
           fontSize = 24.sp
           )
       }
        MiniGameTimerComposable(
            viewModel,
            {
                viewModel.finishGame()
                navigate()
            }
        )
    }
}

@Composable
fun falscherButton(offsetX: Dp, text: String, color: Color, onClick : () -> Unit) {
    GenericButton(
        offsetX = offsetX,
        offsetY = 0.dp,
        text = text,
        color = color,
        textColor = Color.Black,
        rotation = 0f) {
        onClick()
    }
}

@Composable
fun richtigerButton(offsetX: Dp, text: String, color:Color, onClick : () -> Unit) {
    GenericButton(
        offsetX = offsetX,
        offsetY = 0.dp,
        text = text,
        color = color,
        textColor = Color.Black,
        rotation = 0f) {
        onClick()
    }
}
@Composable
fun verkacktCard(){
    Card(
        Modifier.fillMaxSize(),
        backgroundColor = Color(255,150,150)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Verkackt!",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}