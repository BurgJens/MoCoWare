package de.testjens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.testjens.model.Game
// import de.testjens.model.Thingi
import de.testjens.ui.theme.MoCoWareTheme
import de.testjens.view.AppNavigation
import de.testjens.viewmodel.GameViewModel
import de.testjens.viewmodel.JoinGameViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  Thingi.name

        val joinGameViewModel: JoinGameViewModel by viewModels()
        val gameViewModel: GameViewModel by viewModels()

        setContent {
            MoCoWareTheme {
                AppNavigation(
                    joinGameViewModel = joinGameViewModel,
                    gameViewModel = gameViewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
