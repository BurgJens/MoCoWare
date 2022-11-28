package com.example.testrobert.viewmodel

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.testrobert.MainActivity
import com.example.testrobert.model.SpielListe
import com.example.testrobert.sensor.SpeedSensor
import java.util.*
import java.util.concurrent.Flow
import kotlin.random.Random

class SpielViewModel():ViewModel(){

    val iPuschen = mutableStateOf(0)

    var speed= mutableStateOf(0)



    val spielReihenfolge = List(30) { Random.nextInt(0, 3) }.toSet().toList()

    var listeSpiele= SpielListe()







    fun startProcess() {
       println(iPuschen.value)

        if (iPuschen.value==30){
            println("tesgv")
        }

    }



}
