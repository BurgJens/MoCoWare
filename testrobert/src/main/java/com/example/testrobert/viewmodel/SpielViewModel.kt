package com.example.testrobert.viewmodel

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testrobert.model.SpielListe
import kotlin.random.Random

class SpielViewModel():ViewModel(){


    val iPuschen = mutableStateOf(0)


    var speedSensorAktiv:Boolean= false
    var accelSensorAktiv:Boolean= false

    var spielIstAktiv:Boolean= false

    var maxXwet=0.0f
    var maxYwet=0.0f
    var maxZwet=0.0f

    private val _timer : MutableLiveData<Int> = MutableLiveData<Int>()
    var timer : LiveData<Int> = _timer

    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
    var speed : LiveData<Double> = _speed

    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
    var accel:LiveData<Array<Float>> = _accel



    var listeSpiele= SpielListe()

    init {
        setSpeed(0.0)
        setTime(30)
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

    fun breack(){
        setSpeed(0.0)
        speed.value?.let { speed.value!!.minus(it) }

    }



    var countDownTimer = object : CountDownTimer(30000, 1000) {

        // override object functions here, do it quicker by setting cursor on object, then type alt + enter ; implement members
        override fun onTick(millisUntilFinished: Long) {
            _timer.postValue(timer.value?.minus(1))
            println(timer.value)
        }

        override fun onFinish() {
            println("Zeit ist vorbei!")
        }
    }





}
