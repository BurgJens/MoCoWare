package de.mocoware.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.viewmodel.CreateGameViewModel

@Composable
fun ScreenLobbyHandler(
    viewModel: CreateGameViewModel,
    navigateGame : () -> Unit
){
    ScreenLobbyRender(
        clickCreateGame = { name: String, rounds: Int, online: Boolean ->
            viewModel.createGameAsHost(name, rounds, online)
            navigateGame()
        }
    )
}

@Composable
fun ScreenLobbyRender(
    clickCreateGame : (String, Int, Boolean) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val textState = remember{mutableStateOf(TextFieldValue())}
        TextField(
            modifier = Modifier
                .padding(vertical = 18.dp),
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = {Text(text = "Game Name")}
        )
        val boolState = remember{mutableStateOf(false)}

        Row(
            modifier = Modifier
                .padding(vertical = 18.dp)
        )
        {
            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    if(boolState.value) MaterialTheme.colors.surface else Color.Green
                )
            ){
                Text(text = "Search Lobby")
            }
                    }
    }
    }

