package de.mocoware.view.screens.minigames

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.model.minigames.DataMGconfusingButtons
import de.mocoware.view.elements.GenericButton
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel

@Composable
fun ScreenMGannoyingButtons(
    viewModel : GameViewModel,
    gameData : DataMGannoyingButtons,
    navigate : () -> Unit,
){
    val context = LocalContext.current as Activity
    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

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
                        FinalButtonAnnoying(
                            offsetX = each.offsetX.dp,
                            offsetY = each.offsetY.dp,
                            rotation = each.rotation)
                        {
                        viewModel.finishGame()
                        navigate()
                        }
                }
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
fun FinalButtonAnnoying(offsetX: Dp, offsetY: Dp, rotation : Float, onClick : () -> Unit) {
    GenericButton(offsetX, offsetY, text = "Here!", color = Color.Green, Color.Black, rotation, onClick)
}

@Composable
fun AnnoyingButton(offsetX: Dp, offsetY: Dp, text: String, color: Color, rotation : Float, clicked : Boolean, onClick: () -> Unit) {
    var visible by remember {mutableStateOf(clicked)}
    if(visible)
        GenericButton(offsetX, offsetY, text, color, Color.Black, rotation) {
            onClick()
            visible = false
        }
}