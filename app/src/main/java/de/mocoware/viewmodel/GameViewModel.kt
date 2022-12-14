package de.mocoware.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.mocoware.model.Game


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
}