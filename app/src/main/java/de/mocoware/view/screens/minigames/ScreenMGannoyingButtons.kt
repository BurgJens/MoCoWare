package de.mocoware.view.screens.minigames

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel

@Composable
fun ScreenMGannoyingButtons(
    viewModel : GameViewModel,
    gameData : DataMGannoyingButtons,
    navigate : () -> Unit,
){

    val context = LocalContext.current as Activity
//    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        for (each in gameData.annoyingButtonList) {
                when (each.finalButton) {
                    false ->
                        AnnoyingButton(
                            offsetX = each.offsetX.dp,
                            offsetY = each.offsetY.dp,
                            text = each.buttonText,
                            color = each.color,
                            rotation = each.rotation,
                            clicked =  each.visible,
                            onClick = {each.click()}
                        )
                    true ->
                        FinalButton(
                            offsetX = each.offsetX.dp,
                            offsetY = each.offsetY.dp,
                            rotation = each.rotation)
                        {
                        viewModel.finishGame()
                        navigate()
                        }
                }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ){
            MiniGameTimerComposable(
                viewModel,
                {
                    viewModel.finishGame()
                    navigate()
                }
            )
        }
    }
}

@Composable
fun GenericButton(offsetX: Dp, offsetY: Dp, text: String, color: Color, rotation : Float, onClick : () -> Unit) {
    Button(
        modifier = Modifier
            .absoluteOffset(offsetX, offsetY)
            .rotate(rotation),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(text = text)
    }
}

@Composable
fun FinalButton(offsetX: Dp, offsetY: Dp, rotation : Float, onClick : () -> Unit) {
    GenericButton(offsetX, offsetY, text = "Here!", color = Color.Green, rotation, onClick)
}

@Composable
fun AnnoyingButton(offsetX: Dp, offsetY: Dp, text: String, color: Color, rotation : Float, clicked : Boolean, onClick: () -> Unit) {
    var visible by remember {mutableStateOf(clicked)}
    if(visible)
        GenericButton(offsetX, offsetY, text, color, rotation) {
            onClick()
            visible = false
        }
}