package de.mocoware.model.minigames

import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.LightSensor
import de.mocoware.sensor.SpeedSensor
import kotlin.random.Random

class MiniGameListe(){

    private var spieleListe = mutableListOf<MiniGame>()

    val beispiel = mutableListOf(
        MiniGame("Druecken",
            "Drücke den Button 30 mal!",
            null
        ), MiniGame("Laufen",
            "Laufe 14 KM/H schnell!",
            SpeedSensor()
        ), MiniGame("Shake",
            "Schüttel dein Handy so stark, du kannst!",
            Accelerometer()
        )
        , MiniGame("Licht",
            "Beleuchte dein Handy so stark, du kannst!",
            LightSensor()
        )
    )

    init {
        spieleListe=beispiel

    }

    fun zufallsMiniGame():MiniGame{
        var random =Random.nextInt(0,spieleListe.size)
        println(random)

        return spieleListe.get(random)
    }


}
