package de.mocoware.model.minigames
import android.app.Service
import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.LightSensor
import de.mocoware.sensor.SpeedSensor


abstract class MiniGame {}

class MiniGame(var name :String,var aufgabe:String,var service: Service?)


class MiniGameListe(){
    var listeMiniGame= mutableListOf<MiniGame>(
        MiniGame("Drücken", "Drücke den Button 30 mal", null),
        MiniGame("Laufen","Laufe 14 KM/H schnell",SpeedSensor()),
        MiniGame("Licht","Halte dein Handy an die hellste Stelle", LightSensor()),
        MiniGame("Shake","Schüttel dein Handy so stark du kannst", Accelerometer())
    )

    fun zufallSpiel():MiniGame{
        var random= kotlin.random.Random.nextInt(0,listeMiniGame.size)

        return listeMiniGame[random]
    }
}