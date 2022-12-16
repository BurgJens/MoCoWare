package de.mocoware.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.mocoware.ui.theme.MoCoWareTheme
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.view.elements.TextFieldStandard
import de.mocoware.viewmodel.CreateGameViewModel
import de.mocoware.viewmodel.JoinGameViewModel


@Composable
fun ScreenCreateGameHandler(
    viewModel: CreateGameViewModel,
    navigateCreateGame : () -> Unit
){
    ScreenCreateGameRender(
        clickCreateGame = { name: String, rounds: Int, online: Boolean ->
            viewModel.createGameAsHost(name, rounds, online)
            navigateCreateGame()
        }
    )
}

@Composable
fun ScreenCreateGameRender(
    clickCreateGame : (String, Int, Boolean) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val textState = remember{mutableStateOf(TextFieldValue()) }
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = {Text(text = "Game Name")}
        )
        
        val intState = remember{mutableStateOf(5) }
        Row(
        ) {
            Button(
                onClick = {intState.value = intState.value - 1},
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
            ){
                Text(text = "-")
            }
            Text(
                modifier = Modifier.padding(24.dp),
                text = intState.value.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {intState.value = intState.value + 1},
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
            ){
                Text(text = "+")
            }
        }
        
        ButtonStandard(
            text = "Create Game",
            modifier = Modifier,
            onClick = {clickCreateGame(textState.value.text,  intState.value, false)}
        )
    }
}

@Preview
@Composable
fun ComposablePreview() {
    MoCoWareTheme() {
        ScreenCreateGameRender(
            {name: String, rounds: Int, online: Boolean ->}
        )
    }
}