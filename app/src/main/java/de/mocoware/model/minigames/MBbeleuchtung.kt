package de.mocoware.model.minigames

import android.app.Service
import de.mocoware.model.MiniGame
import de.mocoware.sensor.LightSensor
import de.mocoware.view.navigation.NavMG

data class DataMGbeleuchtung(override var data: Any):GameData{
}


data class Beleuchtung(
    val testAufgabe: String,
    val service: Service
){
}


class MGbeleuchtung(
    override var gameData: GameData = DataMGbeleuchtung(Beleuchtung(
        "Schüttel dein Handy so stark du kannst!",
        LightSensor()
    )),
    override val gameRoute: String = NavMG.MGbeleuchtung.route
): MiniGame {

}