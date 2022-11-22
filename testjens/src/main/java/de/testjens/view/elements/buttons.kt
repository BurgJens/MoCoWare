package de.testjens.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.testjens.ui.theme.MoCoWareTheme
import de.testjens.viewmodel.AppViewModel

@Composable
fun ButtonNewScreen(
    text: String,
    route: String,
    viewModel: AppViewModel,
    navController: NavController
){
    Button(
        onClick = {
                  navController.navigate(route)
        },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Text(text = text)
    }
}

@Composable
fun ButtonChooseGame(text : String){
    OutlinedButton(
        onClick = { },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.background
        ),
        border = BorderStroke(
            2.dp,
            MaterialTheme.colors.surface
        )
    ) {
        Text(text = text)
    }
}