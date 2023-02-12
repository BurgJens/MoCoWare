package de.mocoware.model



import android.app.Service
import de.mocoware.model.minigames.GameData
import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.LightSensor
import de.mocoware.sensor.SpeedSensor



interface MiniGame{
    var gameData : GameData
    val gameRoute : String
}


