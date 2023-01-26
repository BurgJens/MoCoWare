package de.mocoware.view.elements

import android.os.CountDownTimer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData

@Composable
fun MiniGameTimer(timer : LiveData<Int>){
    val timerTime by timer.observeAsState()
    Text(text = "$timerTime")
}