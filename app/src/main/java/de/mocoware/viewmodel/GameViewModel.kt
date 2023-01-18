package de.mocoware.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.Game
import java.util.*


class GameViewModel() : ViewModel(){


    // LiveDate für die Werte von dern Services
    private val _timer : MutableLiveData<Int> = MutableLiveData<Int>()
    var timer : LiveData<Int> = _timer

    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
    var speed : LiveData<Double> = _speed

    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
    var accel:LiveData<Array<Float>> = _accel

    private var game : Game = Game("anusBenus")
    private val _game : MutableLiveData<Game> = MutableLiveData<Game>(game)
    val liveGame : LiveData<Game> = _game

    // Maximale beschleunigungswerte
    var maxXwet=0.0f
    var maxYwet=0.0f
    var maxZwet=0.0f

    init {
        setSpeed(0.0)
        setTime(30)
    }

    inner class Receiver: BroadcastReceiver() {


        override fun onReceive(context: Context, intent: Intent) {
            val speed = Objects.requireNonNull(intent.extras)?.getDouble("speed")

            val axisX = Objects.requireNonNull(intent.extras)?.getFloat("axisX")
            val axisY = Objects.requireNonNull(intent.extras)?.getFloat("axisY")
            val axisZ = Objects.requireNonNull(intent.extras)?.getFloat("axisZ")

            if (speed != null) {
                setSpeed(speed)
            }

            if(axisX != null && axisY!=null && axisZ != null){
                setAcc(axisX,axisY,axisY)

            }
        }
    }

    fun withGame(newGame: Game): GameViewModel{
        setGame(newGame)
        return this
    }

    fun setGame(newGame: Game){
        game = newGame
        _game.postValue(game)
    }
    fun setTime(int: Int){
        _timer.postValue(int)

    }

    fun setSpeed(double: Double){
        _speed.postValue(double)
    }

    fun setAcc(floatX: Float,floatY: Float,floatZ: Float){

        if (floatX>maxXwet) {
            maxXwet=floatX
            _accel.postValue(arrayOf(maxXwet,maxYwet,maxZwet))
        }
        if (floatY>maxYwet) {
            maxYwet=floatY
            _accel.postValue(arrayOf(maxXwet,maxYwet,maxZwet))
        }
        if (floatZ>maxZwet) {
            maxZwet=floatZ
            _accel.postValue(arrayOf(maxXwet,maxYwet,maxZwet))
        }
    }

    var countDownTimer = object : CountDownTimer(30000, 1000) {

        // override object functions here, do it quicker by setting cursor on object, then type alt + enter ; implement members
        override fun onTick(millisUntilFinished: Long) {
            _timer.postValue(timer.value?.minus(1))
        }

        override fun onFinish() {
            println("Zeit ist vorbei!")
        }
    }
}