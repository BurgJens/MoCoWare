package com.example.testalex


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testalex.ui.theme.MoCoWareTheme
import com.google.android.gms.location.*


class MainActivity : ComponentActivity() {




    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setupPermission()
        setContent {
            //val location by applicationViewModel.getLocationLiveData().observeAsState()
            MoCoWareTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Start",
                ) {
                    composable(route = "Start") {
                        StartScreen(navController)
                    }
                    composable(route = "Erstellen") {
                        StartScreen(navController)
                    }
                    composable(route = "Beitreten") {
                        Beitreten(navController)
                    }
                }
            }
        }





    }


    @Composable
    fun StartScreen(
        navController: NavController
    ) {

        BoxWithConstraints {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                PressIconButton(
                    onClick = { navController.navigate("Erstellen") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                    text = { Text("Erstellen") }


                )
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }
        }
    }

    @Composable
    fun PressIconButton(
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        text: @Composable () -> Unit,
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource =
            remember { MutableInteractionSource() },
    ) {


        val isPressed by interactionSource.collectIsPressedAsState()
        val color = if (isPressed) Color.Green else Color.Blue
        Button(
            onClick = onClick, modifier = modifier,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            AnimatedVisibility(visible = isPressed) {
                if (isPressed) {
                    Row {
                        icon()
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))


                    }
                }
            }
            text()
        }
    }


    @Composable
    fun Beitreten(
        navController: NavController
    ) {
        val padding = 16.dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Image(

                    painter = painterResource(id = R.drawable.huepfen),
                    contentDescription = null,
                )
                Spacer(Modifier.size(padding))
                Column {
                    Text("Lobby 1")
                    Text("3 Spieler")
                }
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }
            Row() {
                Image(

                    painter = painterResource(id = R.drawable.schatz),
                    contentDescription = null,
                )
                Spacer(Modifier.size(padding))
                Column {
                    Text("Lobby 2")
                    Text("1 Spieler")
                }
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }
            Row() {
                Image(

                    painter = painterResource(id = R.drawable.huepfen),
                    contentDescription = null,
                )
                Spacer(Modifier.size(padding))
                Column {
                    Text("Lobby 3")
                    Text("14 Spieler")
                }
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }


        }
    }
}



