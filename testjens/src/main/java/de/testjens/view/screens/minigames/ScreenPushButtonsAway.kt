package de.testjens.view.screens.minigames

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.testjens.ui.theme.MoCoWareTheme
import de.testjens.view.screens.ScreenCreateGameRender

@Composable
fun ScreenPushButtonsAwayHandler(
){
}

@Composable
fun ScreenPushButtonsAwayRender(
){
    Box(modifier = Modifier){
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Bla")
        }
    }

}

//@Preview(device = "id_pixel_5")
//@Composable
//fun ComposablePreview() {
//    MoCoWareTheme() {
//        ScreenCreateGameRender(
//            {name: String, rounds: Int, online: Boolean ->}
//        )
//    }
//}