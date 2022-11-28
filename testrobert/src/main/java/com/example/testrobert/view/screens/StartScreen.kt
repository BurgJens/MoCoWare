package com.example.testrobert




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController



@Composable
fun StartScreen(
    viewModel: ViewModel,
    navController: NavController
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column() {
            Button(
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    navController.navigate(NavRoutes.Erstellen.route) // ändern wenn mehr screens erstellt

                }) {
                Text(text = "Erstellen")
            }
            Button(
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    navController.navigate(NavRoutes.Beitreten.route) // ändern wenn mehr screens erstellt

                }) {
                Text(text = "Beitreten")
            }
        }
        // Box(){
        //     Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
        //     Text(text = "über dem Foro")
        // }

    }
}








