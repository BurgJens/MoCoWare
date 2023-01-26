package de.mocoware.model.minigames
import android.app.Service
import de.mocoware.model.Game
import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.LightSensor
import de.mocoware.sensor.SpeedSensor


interface MiniGame{
    var gameData : GameData
    val gameRoute : String
}














































class MiniGame2(var name :String, var aufgabe:String, var service: Service?)


class MiniGameListe(){
    var listeMiniGame= mutableListOf<MiniGame2>(
        MiniGame2("Drücken", "Drücke den Button 30 mal", null),
        MiniGame2("Laufen","Laufe 14 KM/H schnell",SpeedSensor()),
        MiniGame2("Licht","Halte dein Handy an die hellste Stelle", LightSensor()),
        MiniGame2("Shake","Schüttel dein Handy so stark du kannst", Accelerometer())
    )

    fun zufallSpiel():MiniGame2{
        var random= kotlin.random.Random.nextInt(0,listeMiniGame.size)

        return listeMiniGame[random]
    }
}