package de.mocoware.view.screens.minigames

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.DataMGannoyingButtons
import de.mocoware.model.minigames.MGannoyingButtons
import de.mocoware.model.minigames.MiniGame

@Composable
fun ScreenMGannoyingButtons(
    gameData : MGannoyingButtons,
    update : () -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        for (each in gameData.buttonList.reversed()){
            AnimatedVisibility(
                visible = !each.getClicked()
            ) {
                when (each.finalButton) {
                    false ->
                        Button(
                            modifier = Modifier.absoluteOffset(each.offsetX.dp, each.offsetY.dp)
                                .rotate(each.rotation),
                            onClick = {
                                each.click()
                                update()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan)
                        ) {
                            Text(text = each.text)
                        }
                    true ->
                        Button(
                            modifier = Modifier.absoluteOffset(each.offsetX.dp, each.offsetY.dp)
                                .rotate(each.rotation),
                            onClick = {
                                each.click()
                                update()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                        ) {
                            Text(text = "Here!")
                        }
                }
            }
        }
    }
}