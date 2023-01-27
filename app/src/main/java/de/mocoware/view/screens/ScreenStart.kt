package de.mocoware.view.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.example.testrobert.Permission
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.viewmodel.JoinGameViewModel


@Composable
fun ScreenStartHandler(
    viewModel: JoinGameViewModel,
    navigateNewGame: () -> Unit,
    navigateJoinGame: () -> Unit,
    navigateTest: () -> Unit,
    context: Context
){
    ScreenStartRender(
        navigateNewGame = navigateNewGame,
        navigateJoinGame = navigateJoinGame,
        navigateTest = navigateTest,
        context = context
    )
}

@Composable
fun ScreenStartRender(
    navigateNewGame: () -> Unit,
    navigateJoinGame: () -> Unit,
    navigateTest: () -> Unit,
    context:Context
){
//    LocalLifecycleOwner.

    Permission(
    permissionNotAvailableContent = {
        Column(Modifier.fillMaxSize()) {
            Text("O noes! No Camera!")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    context.startActivity(
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                    )
                }
            ) {
                Text("Öffne deine Einstellungen")
            }
        }
    }
)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ButtonStandard(
                text = "New Game",
                modifier = Modifier.padding(20.dp),             // Abstand auf 20 (+20) dp ! finde ich besser und ihr ?
                onClick = navigateNewGame
            )
            ButtonStandard(
                text = "Join Game",
                modifier = Modifier.padding(20.dp),
                onClick = {navigateJoinGame()
                    Log.d("Button", "Was clicked")
                }
            )
//            ButtonStandard(
//                text = "Test",
//                modifier = Modifier,
//                onClick = {navigateTest()
//                    Log.d("Button", "Was clicked")
//                }
//            )
        }
}