package de.mocoware.view.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.mocoware.view.elements.GenericButton
import de.mocoware.viewmodel.UserNameViewModel

@Composable
fun ScreenEnterUserName(
    navigate : () -> Unit,
    viewModel: UserNameViewModel
){
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val textState = remember{ mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier
                .padding(vertical = 18.dp),
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = {
                Text(
                    text = "Enter Username",
                    textAlign = TextAlign.Center
                )
            }
        )

        GenericButton(
            offsetX = 0.dp,
            offsetY = 0.dp,
            text = "Confirm",
            color = Color.Black,
            textColor = Color.White,
            rotation = 0f
        ) {
            if(textState.value.text == ""){

            }else{
                println(textState.value.text)
                viewModel.setAppPlayername(context, textState.value.text)
                navigate()
            }
        }
    }
}