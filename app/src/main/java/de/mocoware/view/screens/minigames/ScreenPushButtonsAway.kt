package de.mocoware.view.screens.minigames

import androidx.compose.ui.tooling.preview.Preview
import de.mocoware.ui.theme.MoCoWareTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.AnnoyingButton
import de.mocoware.model.minigames.PushButtonsAway


//@Composable
//fun ScreenPushButtonsAwayHandler(
//){
//
//
//    ScreenPushButtonsAwayRender(
//
//    )
//}

@Composable
fun ScreenPushButtonsAwayRender(
    buttonList : MutableList<AnnoyingButton>,
    update : () -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        for (each in buttonList.reversed()){
            if (!each.getClicked()){
                if(!each.finalButton){
                    Button(
                        modifier = Modifier.
                        absoluteOffset(each.offsetX.dp,each.offsetY.dp),
                        onClick = {
                            each.click()
                            update()
                                  },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan)
                    ) {
                        Text(text = "Test")
                    }
                } else{
                    Button(
                        modifier = Modifier.
                        absoluteOffset(each.offsetX.dp,each.offsetY.dp),
                        onClick = {
                            each.click()
                            update()
                                  },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                    ) {
                        Text(text = "Test")
                    }
                }
            }
        }
    }
}

//@Preview(device = "id:pixel_5")
//@Composable
//fun ComposablePreview() {
//    val test = PushButtonsAway()
//    MoCoWareTheme() {
//        ScreenPushButtonsAwayRender(
//            test.buttonList
//        )
//    }
//}