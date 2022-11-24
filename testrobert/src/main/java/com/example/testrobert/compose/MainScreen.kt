package com.example.testrobert




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun StartScreen(
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


                    navController.navigate("Erstellen") // ändern wenn mehr screens erstellt

                }) {
                Text(text = "Erstellen")
            }
            Button(
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    navController.navigate("Beitreten") // ändern wenn mehr screens erstellt

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


@Composable
fun ErstellenScreen(
    navController: NavController,
    startSpeed: ()->Unit,
    stopSpeed:()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 100.dp, 0.dp, 100.dp),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                stopSpeed()
                navController.navigate(NavRoutes.Erstellen.route) // ändern wenn mehr screens erstellt
            }) {
                Text(text = "stop")
            }
            Button(onClick = {

                startSpeed()
                navController.navigate(NavRoutes.Erstellen.route) // ändern wenn mehr screens erstellt

            }) {
                Text(text = "start")
            }
            LazyColumn(
            ) {
                items(350) { index ->
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .width(300.dp)
                            .padding(15.dp)
                            .background(Color.Black.copy(0.5f)),
                    ) {
                    }
                }
            }

        }

    }
}


@Composable
fun BeitretenScreen(
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 100.dp, 0.dp, 100.dp),
        contentAlignment = Alignment.Center
    )
    {
        LazyColumn(
        ) {
            items(350) { index ->
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(300.dp)
                        .padding(15.dp)
                        .background(Color.Black.copy(0.5f)),
                ) {
                }
            }
        }
    }
}

@Composable
fun CameraScreen(
    navController: NavController
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Button(onClick = {

        }) {

        }

    }
}




