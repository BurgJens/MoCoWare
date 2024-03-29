package de.mocoware.model.minigames

import android.app.Service
import de.mocoware.model.MiniGame
import de.mocoware.sensor.SpeedSensor
import de.mocoware.view.navigation.NavMG


data class DataMGlaufenWithService(override var data: Any):GameData{
    var text="Laufe 14 KM/H schnell !!!"
}


data class LaufenWithService(
    val testAufgabe: String,
    val service: Service
){

}



class MGlaufenWithService(
    override var gameData: GameData = DataMGlaufenWithService(LaufenWithService(
        "Laufe 14 KM/H schnell !!!",
        SpeedSensor()
    )),
    override val gameRoute: String = NavMG.MGlaufenWithSerivce.route
): MiniGame {

}