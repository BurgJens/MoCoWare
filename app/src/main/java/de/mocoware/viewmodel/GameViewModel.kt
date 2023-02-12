package de.mocoware.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import de.mocoware.model.Game
import de.mocoware.model.MiniGameTimer
import de.mocoware.view.navigation.NavMG
import java.util.*


class GameViewModel : ViewModel(){
    var axisXGyro:Float= 0.0F       // Für Gyroskope start Orientierung
    var axisYGyro:Float= 0.0F       // ..
    var axisZGyro:Float= 0.0F       // ..




    inner class Receiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val speed = Objects.requireNonNull(intent.extras)?.getDouble("speed")

            val axisX = Objects.requireNonNull(intent.extras)?.getFloat("axisX")
            val axisY = Objects.requireNonNull(intent.extras)?.getFloat("axisY")
            val axisZ = Objects.requireNonNull(intent.extras)?.getFloat("axisZ")

            val axisXGyroReciv = Objects.requireNonNull(intent.extras)?.getFloat("axisXGyro")
            val axisYGyroReciv = Objects.requireNonNull(intent.extras)?.getFloat("axisYGyro")
            val axisZGyroreciv = Objects.requireNonNull(intent.extras)?.getFloat("axisZGyro")

            if(axisXGyroReciv != null && axisYGyroReciv!=null && axisZGyroreciv != null){
                axisXGyro+=axisXGyroReciv
                println(axisXGyro)

                axisYGyro+=axisYGyroReciv
                println(axisYGyro)

                axisZGyro+=axisZGyroreciv
                println(axisZGyro)

            }

          // if (speed != null) {
          //     println(speed)
          // }

          // if(axisX != null && axisY!=null && axisZ != null){
          //     println(axisX)
          //     println(axisY)
          //     println(axisZ)
          // }
        }
    }

    var game = Game("Bla")

    var currentMG = game.getCurrentGame()

    var currentGameData = currentMG.gameData

//    val gameDataLive = MutableLiveData<GameData>()

    var routeToMG = currentMG.gameRoute

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
        println("                                                                    $routeToMG")

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
































//
//
//    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
//    var speed : LiveData<Double> = _speed
//
//    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
//    var accel:LiveData<Array<Float>> = _accel
//
//////    private var game : Game = Game("anusBenus")
////    private val _game : MutableLiveData<Game> = MutableLiveData<Game>(game)
////    val liveGame : LiveData<Game> = _game
//
//    // Maximale beschleunigungswerte
//    var maxXwet=0.0f
//    var maxYwet=0.0f
//    var maxZwet=0.0f
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
//    fun setAcc(floatX: Float,floatY: Float,floatZ: Float){
//
//        if (floatX>maxXwet) {
//            maxXwet=floatX
//            _accel.postValue(arrayOf(maxXwet,maxYwet,maxZwet))
//        }
//        if (floatY>maxYwet) {
//            maxYwet=floatY
//            _accel.postValue(arrayOf(maxXwet,maxYwet,maxZwet))
//        }
//        if (floatZ>maxZwet) {
//            maxZwet=floatZ
//            _accel.postValue(arrayOf(maxXwet,maxYwet,maxZwet))
//        }
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

