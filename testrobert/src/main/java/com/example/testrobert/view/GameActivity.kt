package com.example.testrobert.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.testrobert.sensor.SpeedSensor
import com.example.testrobert.view.screens.Puschen
import com.example.testrobert.view.screens.justGames.Laufen
import kotlinx.coroutines.GlobalScope
import java.sql.Time
import java.time.LocalDateTime
import kotlin.random.Random

class GameActivity: ComponentActivity() {

    var puschenWarSchon = false
    var laufenWarSchon = false


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val iPuschen = mutableStateOf(0)




        setContent {

            var random = Random.nextInt(0, 3)

            if (random == 0) {
                println(iPuschen)
                Puschen(iPuschen)
            }
            if (random == 1) {
                println(iPuschen)
                Laufen()
            }
            if (random == 2) {
                


            }
        }
    }
}