package de.testjens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import de.testjens.ui.theme.MoCoWareTheme
import de.testjens.view.AppNavigation
import de.testjens.viewmodel.JoinGameViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: JoinGameViewModel by viewModels()

        setContent {
            MoCoWareTheme {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
