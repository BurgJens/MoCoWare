package com.example.testalex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testalex.ui.theme.MoCoWareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoCoWareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    WithConstraintsComposable()


                }
            }
        }
    }
}




@Composable
fun WithConstraintsComposable() {
    val padding = 16.dp
    BoxWithConstraints {
        Column(
            Modifier
                .padding(padding)
        ) {
                Spacer(Modifier.size(padding))
                FirstGame()
                Spacer(Modifier.size(padding))
                SecondGame()
                Spacer(Modifier.size(padding))
                ThirdGame()
                Spacer(Modifier.size(padding))
                FourthGame()



        }
    }
}

@Composable
fun FirstGame() {
    val padding = 16.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End

    ) {
        Image(
            painter = painterResource(id = R.drawable.schatz),
            contentDescription = null,
        )
        Spacer(Modifier.size(padding))
    Column {
        Text("Schatzsuche")
        Text("2-10 Spieler")

    }

        PressIconButton(
            onClick = {},
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
            text = { Text("Neue Lobby") },
            Modifier.scale(0.8F)
        )
        PressIconButton(
            onClick = {},
            icon = { Icon(Icons.Filled.Refresh, contentDescription = null) },
            text = { Text("Random") },
            Modifier.scale(0.8F)
        )


    }
}

@Composable
fun SecondGame() {
    val padding = 16.dp
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(

                painter = painterResource(id = R.drawable.huepfen),
                contentDescription = null,
            )
            Spacer(Modifier.size(padding))
            Column {
                Text("Hüpfen")
                Text("2-10 Spieler")
            }
            /*Spacer(Modifier.size(padding))*/
            PressIconButton(
                onClick = {},
                icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                text = { Text("Neue Lobby") },
                Modifier.scale(0.8F)
            )
            PressIconButton(
                onClick = {},
                icon = { Icon(Icons.Filled.Refresh, contentDescription = null) },
                text = { Text("Random") },
                Modifier.scale(0.8F)
            )

        }
    }

@Composable
fun ThirdGame() {
    val padding = 16.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Image(

            painter = painterResource(id = R.drawable.huepfen),
            contentDescription = null,
        )
        Spacer(Modifier.size(padding))
        Column {
            Text("Würfelspiel")
            Text("2-4 Spieler")
        }
        PressIconButton(
            onClick = {},
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
            text = { Text("Neue Lobby") },
            Modifier.scale(0.8F)
        )
        PressIconButton(
            onClick = {},
            icon = { Icon(Icons.Filled.Refresh, contentDescription = null) },
            text = { Text("Random") },
            Modifier.scale(0.8F)
        )

    }
}
@Composable
fun FourthGame() {
    val padding = 16.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Image(

            painter = painterResource(id = R.drawable.huepfen),
            contentDescription = null,
        )
        Spacer(Modifier.size(padding))
        Column {
            Text("Gangbang")
            Text("5-20 Spieler")
        }
        PressIconButton(
            onClick = {},
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
            text = { Text("Neue Lobby") },
            Modifier.scale(0.8F)
        )
        PressIconButton(
            onClick = {},
            icon = { Icon(Icons.Filled.Refresh, contentDescription = null) },
            text = { Text("Random") },
            Modifier.scale(0.8F)
        )

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
    Button(onClick = onClick, modifier = modifier,
        interactionSource = interactionSource) {
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



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoCoWareTheme {
        FirstGame()
    }
}