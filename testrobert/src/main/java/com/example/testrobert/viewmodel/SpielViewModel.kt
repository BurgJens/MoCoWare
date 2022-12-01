package com.example.testrobert.viewmodel

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

    var maxXwet=0.0f
    var maxYwet=0.0f
    var maxZwet=0.0f




    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
    var speed : LiveData<Double> = _speed

    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
    var accel:LiveData<Array<Float>> = _accel

    val spielReihenfolge = List(30) { Random.nextInt(0, 3) }.toSet().toList()

    var listeSpiele= SpielListe()

    init {
        setSpeed(0.0)
    }


   // var iAccel =Intent(this@MainActivity,Accelerometer::class.java)
   // var iSpeed =Intent(this@MainActivity,SpeedSensor::class.java)

    fun startstopSpeedService(test:()->Unit){
        test()
    }


    fun ist14Schnell(double: Double):Boolean{
        return double>14
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


    fun startProcess() {
       println(iPuschen.value)

        if (iPuschen.value==30){
            println("tesgv")
        }

    }



}
