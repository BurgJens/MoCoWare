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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.mocoware.ui.theme.MoCoWareTheme
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.viewmodel.CreateGameViewModel


@Composable
fun ScreenCreateGameHandler(
    viewModel: CreateGameViewModel,
    navigateGame : () -> Unit
){
    ScreenCreateGameRender(
        clickCreateGame = { name: String, rounds: Int, online: Boolean ->
            viewModel.createGameAsHost(name, rounds, online)
            navigateGame()
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

        val textState = remember{mutableStateOf(TextFieldValue())}
        TextField(
            modifier = Modifier
                .padding(vertical = 18.dp),
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = {Text(text = "Game Name")}
        )
        
        val intState = remember{mutableStateOf(5) }
        Text(
            modifier = Modifier
                .padding(top = 18.dp),
            text= "Rounds:"
        )
        Row(
            modifier = Modifier
                .padding(bottom = 18.dp)
        ) {
            Button(
                onClick = {
                    if (intState.value > 1) {
                        intState.value = intState.value - 1
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                modifier = Modifier.size(40.dp, 40.dp)
            ){
                Text(text = "-")
            }
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = intState.value.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    if (intState.value < 20) {
                        intState.value = intState.value + 1
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                modifier = Modifier.size(40.dp, 40.dp)
            ){
                Text(text = "+")
            }
        }

        val boolState = remember{mutableStateOf(false)}


        Row(
            modifier = Modifier
                .padding(vertical = 18.dp)
        ){
            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = {boolState.value = false},
                colors = ButtonDefaults.buttonColors(
                    if(boolState.value) MaterialTheme.colors.surface else Color.Green
                )
            ){
                Text(text = "offline")
            }
            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = {},
//                onClick = {boolState.value = true},
                colors = ButtonDefaults.buttonColors(
                    if(boolState.value) Color.Green else MaterialTheme.colors.surface
                )
            ){
                Text(text = "online")
            }
        }
        ButtonStandard(
            modifier = Modifier
                .padding(vertical = 80.dp),
            text = "Create Game",
            onClick = {
                if (textState.value.text != "") 
                    clickCreateGame(textState.value.text,  intState.value, boolState.value)
                else
                    {}
            }
        )
    }
}

@Composable
fun SimpleAlertDialog() {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = {})
            { Text(text = "OK") }
        },
    )
}

@Preview(device = "id_pixel_5")
@Composable
fun ComposablePreview() {
    MoCoWareTheme() {
        ScreenCreateGameRender(
            {name: String, rounds: Int, online: Boolean ->}
        )
    }
}