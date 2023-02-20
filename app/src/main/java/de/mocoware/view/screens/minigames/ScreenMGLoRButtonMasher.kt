
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
import coil.size.Scale
import de.mocoware.model.minigames.DataMGLoRButtonMasher
import de.mocoware.view.elements.GenericButton
import de.mocoware.view.elements.GenericScaleButton
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel

@Composable
fun ScreenMGLoRButtonMasher(
    viewModel : GameViewModel,
    gameData : DataMGLoRButtonMasher,
    navigate : () -> Unit,
){

    var lordata = viewModel.currentMG.gameData

    val context = LocalContext.current as Activity
    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE



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

             Row(
                 Modifier.fillMaxSize(),
                 horizontalArrangement = Arrangement.Center,
                 verticalAlignment = Alignment.CenterVertically
             ) {


                 FalscherButton(
                     scale= 0.dp
                 ){

                 }


                 NextCardButton(
                     scale= 0.dp
                 ) {viewModel.wertButtonMasher()}
             }


        }

    }

        MiniGameTimerComposable(
            viewModel,
            {
                viewModel.finishGame({navigate()})

            }
        )


}

@Composable
fun FalscherButton(scale: Dp, onClick : () -> Unit) {
    GenericScaleButton(
        scale = 390.dp,
        text = "",
        color = Color.Gray,
        Color.Black,
        onClick
    )
}
//@Composable
//fun NextCardButton(onClick: () -> Unit){
//    Button(
//        onClick = onClick,
//        modifier = Modifier.scale(3.0F,5.0F),
//        colors = buttonColors(Color.Green)
//        ) {
//        Text("PUSH ME", color = Color.Black)
//        onClick()
//    }
//}

@Composable
fun NextCardButton(scale: Dp, onClick : () -> Unit){
    GenericScaleButton(
        scale = 390.dp,
        text = "",
        color = Color.Green,
        Color.Black,
        onClick
    )
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

//@Composable
//fun ButtonCard(viewModel : GameViewModel){
//    Card (
//        Modifier.fillMaxSize()
//    ){
//        Row(
//            Modifier.fillMaxSize(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//
//            FalscherButton(){}
//
//
//            NextCardButton {viewModel.wertButtonMasher()}
//        }
//    }
//
//}



