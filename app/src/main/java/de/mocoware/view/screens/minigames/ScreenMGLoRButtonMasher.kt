
package de.mocoware.view.screens.minigames

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.OffsetEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.size.Scale
import de.mocoware.model.Game
import de.mocoware.model.minigames.DataMGLoRButtonMasher
import de.mocoware.view.elements.GenericButton
import de.mocoware.view.elements.GenericScaleButton
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel
import kotlin.random.Random

@Composable
fun ScreenMGLoRButtonMasher(
    viewModel : GameViewModel,
    gameData : DataMGLoRButtonMasher,
    navigate : () -> Unit,
){


    val context = LocalContext.current as Activity
    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    var failed by remember {mutableStateOf(false)}

    Card(
        Modifier.fillMaxSize(),
        backgroundColor = Color(255,255,255)

    ) {
         Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

        ) {
             Text(
                 text = "Drück 20 mal auf den Grünen Button",
                 fontSize = 24.sp,
                 color = Color.Black
             )

             Box(
                 Modifier.fillMaxSize(),
                 Alignment.Center

             ) {for (each in gameData.buttonCardList){
                 when (each.finalCard){
                     false ->
                         ButtonCard(
                             onClickRight = {},
                             onClickWrong = { failed = true},
                             each.leftcorrectButton
                         )


                     true ->
                         ButtonCard(
                             onClickRight = {viewModel.finishGame({navigate()}, true)},
                             onClickWrong = { failed = true},
                             each.leftcorrectButton
                         )

                 }
             }
             }

        }


    }

    if(failed){
        VerkacktCard()
    }

        MiniGameTimerComposable(
            viewModel,
            {
                viewModel.finishGame({navigate()}, false)

            }
        )


}

@Composable
fun FalscherButton(offsetX: Dp, scale: Dp, onClick : () -> Unit) {
    GenericScaleButton(
        offsetX = offsetX,
        scale = 390.dp,
        text = "",
        color = Color.Gray,
        textColor = Color.Black){
        onClick()
    }
}

@Composable
fun NextCardButton(offsetX: Dp,scale: Dp,  onClick : () -> Unit){
    GenericScaleButton(
        offsetX = offsetX,
        scale = 390.dp,
        text = "",
        color = Color.Green,
        textColor = Color.Black){
        onClick()
    }

}
@Composable
fun ButtonCard(onClickRight: () -> Unit, onClickWrong:()->Unit, leftRightRandom : Boolean){
    var cardVisibleState by remember {mutableStateOf(true)}

    var correctButtonOffset = 200.dp
    var wrongButtonOffset = -200.dp

    if(leftRightRandom){
        correctButtonOffset = -200.dp
        wrongButtonOffset = 200.dp
    }

    if (cardVisibleState) {
        Card(
            Modifier.fillMaxSize()
        ) {
            Box(
                //Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {

                NextCardButton(
                    offsetX = correctButtonOffset,
                    scale = 390.dp,
                ) {
                    onClickRight()
                    cardVisibleState = false
                }

                FalscherButton(
                    offsetX = wrongButtonOffset,
                    scale = 390.dp,
                ) {
                    onClickWrong()
                    cardVisibleState = false

                }
            }
        }
    }
}




@Composable
fun VerkacktCard(){
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





