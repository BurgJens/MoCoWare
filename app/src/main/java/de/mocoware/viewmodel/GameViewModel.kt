package de.mocoware.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.service.autofill.OnClickAction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import de.mocoware.model.Game
import de.mocoware.model.HighScore
import de.mocoware.model.MiniGameTimer
import de.mocoware.model.minigames.*
import de.mocoware.view.navigation.NavMG
import java.lang.Thread.sleep
import java.util.*


class GameViewModel : ViewModel(){


    val highscores = listOf<HighScore>(HighScore("test","test",false,5))

    var game = Game("Bla")

    var currentMG = game.getCurrentMG()

    var currentGameData = currentMG.gameData

    val wonGames = mutableListOf<Boolean>()


//  val gameDataLive = MutableLiveData<GameData>()

    var routeToMG = currentMG.gameRoute

    // Für Gyroskope start Orientierung
    var axisXGyro:Float= 0.0F
    var axisYGyro:Float= 0.0F
    var axisZGyro:Float= 0.0F

    // Maximale beschleunigungswerte
    var maxXwert=0.0f
    var maxYwert=0.0f
    var maxZwert=0.0f

    var serviceAccelIstAktiv=false
    var serviceSpeedIstAktiv=false
    var serviceLightIstAktiv=false
    var serviceGyrpIstAktiv=false

    var lichtWert=true

    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
    var accel: LiveData<Array<Float>> = _accel

    private val _light: MutableLiveData<Float> = MutableLiveData<Float>()
    var light: LiveData<Float> = _light

    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
    var speed : LiveData<Double> = _speed


    // Nur wegen cast problem
    var gameDatMGannoyingButtons = MGannoyingButtons().gameData as DataMGannoyingButtons
    var gameDatMGlaufenWithService = MGlaufenWithService().gameData  as DataMGlaufenWithService
    var gameDatMGconfusingButtons = MGconfusingButtons().gameData as DataMGconfusingButtons
    var gameDatMGshake = MGshake().gameData as DataMGshake
    var gameDatMGbeleuchtung = MGbeleuchtung().gameData  as DataMGbeleuchtung
    var gameDatMGLoRButtonMasher = MGLoRButtonMasher().gameData as DataMGLoRButtonMasher



     // Receiver für die Services
    inner class Receiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val speed = Objects.requireNonNull(intent.extras)?.getDouble("speed")

            val licht = Objects.requireNonNull(intent.extras)?.getFloat("lichtWert")

            val axisX = Objects.requireNonNull(intent.extras)?.getFloat("axisX")
            val axisY = Objects.requireNonNull(intent.extras)?.getFloat("axisY")
            val axisZ = Objects.requireNonNull(intent.extras)?.getFloat("axisZ")

            val axisXGyroReciv = Objects.requireNonNull(intent.extras)?.getFloat("axisXGyro")
            val axisYGyroReciv = Objects.requireNonNull(intent.extras)?.getFloat("axisYGyro")
            val axisZGyroreciv = Objects.requireNonNull(intent.extras)?.getFloat("axisZGyro")

            if(axisXGyroReciv != null && axisYGyroReciv!=null && axisZGyroreciv != null){
                axisXGyro+=axisXGyroReciv

                axisYGyro+=axisYGyroReciv

                axisZGyro+=axisZGyroreciv

            }

            if (speed != null) {
                _speed.postValue(speed)
            }

            if (licht != null) {
                if (_light.value==null) _light.value=licht
                if (licht > _light.value!!) _light.value=licht


            }
            if(axisX != null && axisY!=null && axisZ != null){
               setAcc(axisX,axisY,axisZ)
            }
        }
    }



   init {
       println("test${currentMG.gameRoute}")
       setAcc(1f,1f,1f)
       _light.postValue(0.1f)
   }


    fun updateMGdata(){
        when (currentMG.gameData){
            is DataMGannoyingButtons ->{
                gameDatMGannoyingButtons = currentMG.gameData as DataMGannoyingButtons
            }
            is DataMGlaufenWithService ->{
                gameDatMGlaufenWithService = currentMG.gameData as DataMGlaufenWithService
            }
            is DataMGconfusingButtons ->{
                gameDatMGconfusingButtons = currentMG.gameData as DataMGconfusingButtons
            }
            is DataMGshake ->{
                gameDatMGshake = currentMG.gameData as DataMGshake
            }
            is DataMGbeleuchtung ->{
                gameDatMGbeleuchtung = currentMG.gameData as DataMGbeleuchtung
            }
            is DataMGLoRButtonMasher ->{
                gameDatMGLoRButtonMasher = currentMG.gameData as DataMGLoRButtonMasher
            }
        }
    }

    fun finishGame(navigate : () -> Unit, won : Boolean = false){

        wonGames.add(won)

        val nextGame = game.nextGame()
        currentMG = game.getCurrentMG()
        currentGameData = currentMG.gameData

        if(nextGame){
            routeToMG = game.getRouteToMG()
        }else{
            routeToMG = NavMG.Lobby.route
        }
        _light.postValue(0f)
        _accel.postValue(arrayOf(0.0f,0f,0f))

        updateMGdata()

        navigate()
    }

    val timeToPlay = 10
    val timeToStart = 3

    var gameTimer = MiniGameTimer(timeToPlay)
    fun resetGameTimer(){
        gameTimer = MiniGameTimer(timeToPlay)
    }

    var gameStartTimer = MiniGameTimer(timeToStart)
    fun resetGameStartTimer(){
        gameStartTimer = MiniGameTimer(timeToStart)
    }

    fun setAcc(floatX: Float,floatY: Float,floatZ: Float){


       if (floatX>maxXwert) {
           maxXwert=floatX
           _accel.postValue(arrayOf(maxXwert,maxYwert,maxZwert))
       }
       if (floatY>maxYwert) {
           maxYwert=floatY
           _accel.postValue(arrayOf(maxXwert,maxYwert,maxZwert))
       }
       if (floatZ>maxZwert) {
           maxZwert=floatZ
           _accel.postValue(arrayOf(maxXwert,maxYwert,maxZwert))
       }
    }
}

