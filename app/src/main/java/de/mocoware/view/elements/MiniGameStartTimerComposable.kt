package de.mocoware.view.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.mocoware.viewmodel.GameViewModel

@Composable
fun MiniGameStartTimerComposable(
    viewModel : GameViewModel,
    onTimeFinished : () -> Unit,
){
    val isTimeUp by viewModel.gameStartTimer.isTimeUp.observeAsState()
    val time by viewModel.gameStartTimer.time.observeAsState()

    var shouldNavigate by remember { mutableStateOf(true) }
    var firstDraw by remember { mutableStateOf(true) }

    if (firstDraw){
        firstDraw = false
        viewModel.gameStartTimer.tryStart()
        viewModel.resetGameTimer()
    }

    if (isTimeUp!! && shouldNavigate){
        shouldNavigate = false
        onTimeFinished()
    }

    Card(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "$time",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 64.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}
