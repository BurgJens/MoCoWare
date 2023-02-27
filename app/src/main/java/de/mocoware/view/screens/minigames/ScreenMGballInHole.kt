package de.mocoware.view.screens.minigames

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mocoware.model.minigames.DataMGballInHole
import de.mocoware.model.minigames.GameData
import de.mocoware.viewmodel.GameViewModel
import kotlin.math.abs

@Composable
fun ScreenMGballInHole(
    viewModel : GameViewModel,
    gameData : DataMGballInHole,
    navigate : () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Circle(x = gameData.holePos.first, y = gameData.holePos.second)
        MovingCircle(viewModel = viewModel,2)
    }
}

@Composable
fun Circle(x : Dp, y : Dp) {

    Log.d("TEST", "${Pair(x, y)}")

//    Card(
//        modifier = Modifier
//            .offset(x, y)
//            .size(22.dp),
//        backgroundColor = Color.Black,
//        shape = RoundedCornerShape(11.dp)
//    ) {}

    Card(
        modifier = Modifier
            .offset(300.dp, 0.dp)
            .size(22.dp),
        backgroundColor = Color.Black,
        shape = RoundedCornerShape(11.dp)
    ) {}
}

@Composable
fun MovingCircle(viewModel: GameViewModel, speed : Int) {
    val rotationVector by viewModel.rotationVector.observeAsState()
    var pos = remember{ mutableStateOf(Pair(0.dp,0.dp)) }

    if (rotationVector != null){
        if (rotationVector!!.first > 5f && (pos.value.first + 1.dp * speed * abs(rotationVector!!.first / 10)) < 400.dp){
            pos.value = Pair(pos.value.first + 1.dp * speed,pos.value.second * abs(rotationVector!!.first / 10))
        }
        if (rotationVector!!.first < -5f && (pos.value.first - 1.dp * speed * abs(rotationVector!!.first / 10)) > -400.dp) {
            pos.value = Pair(pos.value.first - 1.dp * speed,pos.value.second * abs(rotationVector!!.first / 10))
        }
//        if (rotationVector!!.second > 5f && (pos.value.second + 1.dp * speed * abs(rotationVector!!.second / 10)) < 100.dp){
//            pos.value = Pair(pos.value.first ,pos.value.second + 1.dp * speed * abs(rotationVector!!.second / 10))
//        }
//        if (rotationVector!!.second < -5f && (pos.value.second - 1.dp * speed * abs(rotationVector!!.second / 10)) > -100.dp){
//            pos.value = Pair(pos.value.first ,pos.value.second - 1.dp * speed  * abs(rotationVector!!.second / 10))
//        }
    }



    Card(
        modifier = Modifier
            .offset(pos.value.first, pos.value.second)
            .size(20.dp),
        backgroundColor = Color.Red,
        shape = RoundedCornerShape(10.dp)
    ) {}
}
