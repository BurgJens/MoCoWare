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



   Box(
       modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
    ) {
       for (each in gameData.ButtonMasherList){
           when(each.winButton) {
               false ->
                   falscherButton(
                                offsetX = each.x,
                                text = "",
                                color = each.color,
                                onClick = { each.click() }
                   )


               true ->
                   richtigerButton(
                                offsetX = each.x,
                                text = "",
                                color = each.color,
                                )
                            {
                                viewModel.finishGame()
                                navigate()
                            }
            }

        }
       Row {
           Text(text = "Drück 20 mal auf den Grünen Button",
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
        textColor = Color.Gray,
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
        textColor = Color.Green,
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

@Composable
fun ButtonCard(){
    Card (
        Modifier.fillMaxSize()
    ){
        Row() {


            falscherButton(
                offsetX = 0.dp,
                text = "",
                color = Color.Black
            ) {

        }
            richtigerButton(
                offsetX = 0.dp,
                text = "",
                color = Color.Green
            ) {

            }
    }}

}