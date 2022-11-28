package com.example.testrobert.model

import androidx.lifecycle.MutableLiveData
import com.example.testrobert.sensor.Accelerometer
import com.example.testrobert.sensor.SpeedSensor


// Mit Event ist ein Spielraum gemeint, welches von den Nutzern erstellt wird. Ist eine Liste von Spielen

class SpielListe(){

    private var spieleListe = mutableListOf<Spiel>()

    val beispiel = mutableListOf(
        Spiel("Druecken",
            "Drücke den Button 30 mal !",
            null
        ), Spiel("Laufen",
            "Laufe 14 KM/H schnell",
            SpeedSensor()
        ), Spiel("Shake",
            "Schüttel dein Handy so stark du kannst",
            Accelerometer()
        )
    )

    init {
        spieleListe=beispiel

    }


}
