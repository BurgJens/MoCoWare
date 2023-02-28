package de.mocoware.view.screens.minigames

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.DataMGballInHole
import de.mocoware.sensor.RotationVector
import de.mocoware.view.elements.MiniGameTimerComposable
import de.mocoware.viewmodel.GameViewModel
import kotlin.math.sqrt

@Composable
fun ScreenMGballInHole(
    viewModel : GameViewModel,
    gameData : DataMGballInHole,
    navigate : () -> Unit
){
    val context = LocalContext.current
    context.startService(Intent(context, RotationVector::class.java))

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

//        Circle(x = gameData.holePos.first, y = gameData.holePos.second)
//        MovingCircle(
//            viewModel = viewModel,0.5f, gameData.holePos.first, gameData.holePos.second){
//            viewModel.finishGame(context, { navigate() }, true)
//        }

        Circle(x = 100.dp, y = 0.dp)
        MovingCircle(
            viewModel = viewModel,0.05f, 100.dp, 0.dp){
            viewModel.finishGame(context, { navigate() }, true)
        }

    }

    MiniGameTimerComposable(viewModel,
        {
            viewModel.finishGame(context, { navigate() })
        }
    )
}

@Composable
fun Circle(x : Dp, y : Dp) {

    Card(
        modifier = Modifier
            .offset(x, y)
            .size(22.dp),
        backgroundColor = Color.Black,
        shape = RoundedCornerShape(11.dp)
    ) {}
}

@Composable
fun MovingCircle(
    viewModel: GameViewModel,
    speedMult : Float,
    finishPosX : Dp,
    finishPosY : Dp,
    finish: () -> Unit
) {
    var firstDraw = remember{mutableStateOf(true)}
    var notFinished = remember{mutableStateOf(true)}

    if (firstDraw.value){
        firstDraw.value = false
        viewModel.ballInHoleCalcStart()
    }

    val ballPos by viewModel.ballPos.observeAsState()

    println(ballPos)

    if(ballPos != null){
        Card(
            modifier = Modifier
                .offset(ballPos!!.first, ballPos!!.second)
                .size(20.dp),
            backgroundColor = Color.Red,
            shape = RoundedCornerShape(10.dp)
        ){}

        val distance = sqrt(
        Math.pow((ballPos!!.first - finishPosX).value.toDouble(), 2.0) +
            Math.pow((ballPos!!.second - finishPosY).value.toDouble(), 2.0)
        )
        if (distance <= 20f && notFinished.value){
            notFinished.value = false
            finish()
        }
    }



}
