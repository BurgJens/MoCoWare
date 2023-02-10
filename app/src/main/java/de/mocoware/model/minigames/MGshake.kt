package de.mocoware.model.minigames

import android.app.Service
import de.mocoware.sensor.SpeedSensor
import de.mocoware.view.navigation.NavMG


data class DataMGshake(override var data: Any):GameData{
}


data class Shake(
    val testAufgabe: String,
    val service: Service
){

}



class MGshake(
    override var gameData: GameData = DataMGshake(Shake(
        "Schüttel dein Handy so stark du kannst!",
        SpeedSensor()
    )),
    override val gameRoute: String = NavMG.MGshake.route
):MiniGame{

}