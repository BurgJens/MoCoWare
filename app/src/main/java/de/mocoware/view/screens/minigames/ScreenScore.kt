package de.mocoware.view.screens.minigames

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.mocoware.MainActivity
import de.mocoware.model.HighScore
import de.mocoware.viewmodel.GameViewModel


@Composable
fun ScreenScore(
    viewModel: GameViewModel,
    context: Context
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LazyColumn {
            items(viewModel.highscores) { entry ->
                Text(
                    text = "${entry.nameSpieler}: ${entry.nameGame}:${entry.bestanden}:${entry.zeit}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
        Button(onClick = { context.startActivity(Intent(context,MainActivity::class.java)) }) {
            Text(text = "Close", color = Color.Black)
        }

    }


}