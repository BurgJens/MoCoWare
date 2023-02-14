package de.mocoware.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.Game
import de.mocoware.model.MiniGameTimer
import de.mocoware.view.navigation.NavMG
import java.util.*


class GameViewModel : ViewModel(){

    var game = Game("Bla")

    var currentMG = game.getCurrentGame()

    var currentGameData = currentMG.gameData

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

    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
    var accel: LiveData<Array<Float>> = _accel

    private val _light: MutableLiveData<Float> = MutableLiveData<Float>()
    var light: LiveData<Float> = _light


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
   }

//    fun updateGamedata(){
//        gameDataLive.postValue(currentGameData)
//    }

    fun finishGame(won : Boolean = false){

        gameTimer._time.postValue(timeToPlay+timeToStart)
        gameTimer._isTimeUp.postValue(false)
        gameTimer.timer.cancel()

        gameStartTimer._time.postValue(timeToStart)
        gameStartTimer._isTimeUp.postValue(false)
        gameStartTimer.timer.cancel()

        val nextGame = game.nextGame()
        currentMG = game.getCurrentGame()
        currentGameData = currentMG.gameData
        if(nextGame){
            routeToMG = game.routeToNextMG()
        }else{
            routeToMG = NavMG.Lobby.route
        }
        println("  $routeToMG")

//        updateGamedata()
    }

    val timeToPlay = 10
    val timeToStart = 3

    val gameTimer = MiniGameTimer(timeToPlay+timeToStart)
    val gameStartTimer = MiniGameTimer(timeToStart)

//        fun setTime(int: Int){
//        _timer.postValue(int)
//
//    }


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






























//
//
//    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
//    var speed : LiveData<Double> = _speed
//

//
//////    private var game : Game = Game("anusBenus")
////    private val _game : MutableLiveData<Game> = MutableLiveData<Game>(game)
////    val liveGame : LiveData<Game> = _game
//

//
////    init {
////        setSpeed(0.0)
////        setTime(30)
////    }
//
////    fun withGame(newGame: Game): GameViewModel{
////        setGame(newGame)
////        return this
////    }
//
////    fun setGame(newGame: Game){
////        game = newGame
////        _game.postValue(game)
////    }

//
//    fun setSpeed(double: Double){
//        _speed.postValue(double)
//    }
//


//
//    fun getCurrentGameName(): String{
//        return GameConnection?.getCurrentGameName() ?: "NoCurrentGame"
//    }
//
//    fun getCurrentGameID(): String{
//        return GameConnection?.getCurrentGameID() ?: "NONE"
//    }
}

