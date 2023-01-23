package com.example.testrobert.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import androidx.annotation.MainThread
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testrobert.MainActivity
import com.example.testrobert.model.Spiel
import com.example.testrobert.model.SpielListe
import com.example.testrobert.sensor.Accelerometer
import java.util.*
import kotlin.random.Random.Default.nextInt

class SpielViewModel():ViewModel(){


    inner class Receiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            // Locationlistener
            val speed = Objects.requireNonNull(intent.extras)?.getDouble("speed")

            // SensorEventListener
            val axisX = Objects.requireNonNull(intent.extras)?.getFloat("axisX")
            val axisY = Objects.requireNonNull(intent.extras)?.getFloat("axisY")
            val axisZ = Objects.requireNonNull(intent.extras)?.getFloat("axisZ")

            // SensorEventListener
            val lightX = Objects.requireNonNull(intent.extras)?.getFloat("lightX")
            val lightY = Objects.requireNonNull(intent.extras)?.getFloat("lightY")
            val lightZ = Objects.requireNonNull(intent.extras)?.getFloat("lightZ")


            if (speed != null) setSpeed(speed)

            if(axisX != null && axisY!=null && axisZ != null){
                setAcc(axisX,axisY,axisY)
            }

            if(lightX != null && lightY!=null && lightZ != null){
                setLight(lightX,lightY,lightZ)
            }
        }
    }

    // Das Spiel welches gespielt wird
    var spiel1: MutableState<Spiel> = mutableStateOf(Spiel("test","testGame",null) )
    var listeSpiele= SpielListe()
    var spielIstAktiv= mutableStateOf(false)

    // Aktive Sensoren
    var speedSensorAktiv= mutableStateOf(false)
    var accelSensorAktiv= mutableStateOf(false)
    var lichtSensorAktiv= mutableStateOf(false)

    // Beschleunigungs Werte
    var maxXwet=0.0f
    var maxYwet=0.0f
    var maxZwet=0.0f

    // Belichtungswerte
    var lightX =0.0f
    var lightY =0.0f
    var lightZ =0.0f

    // Live Werte
    private val _timer : MutableLiveData<Int> = MutableLiveData<Int>()
    var timer : LiveData<Int> = _timer

    private val _speed : MutableLiveData<Double> = MutableLiveData<Double>()
    var speed : LiveData<Double> = _speed

    private val _accel: MutableLiveData<Array<Float>> = MutableLiveData<Array<Float>>()
    var accel:LiveData<Array<Float>> = _accel

    private val _light: MutableLiveData<Float> = MutableLiveData<Float>()
    var light:LiveData<Float> = _light




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

    fun setLight(floatX: Float,floatY: Float,floatZ: Float){
        if (floatX>lightX) {
            lightX=floatX
            _light.postValue((lightX+lightY+lightZ/3))
        }
        if (floatY>lightY) {
            lightY=floatY
            _light.postValue((lightX+lightY+lightZ/3))
        }
        if (floatZ>lightZ) {
            lightZ=floatZ
            _light.postValue((lightX+lightY+lightZ/3))
        }
    }

    fun resetGames(){
        setSpeed(0.0)
        _light.postValue(0.0f)
        _accel.postValue(arrayOf(0.0f,0.0f,0.0f))
        spielIstAktiv.value=false
    }

    var countDownTimer = object : CountDownTimer(31000, 1000) {
        // override object functions here, do it quicker by setting cursor on object, then type alt + enter ; implement members
        override fun onTick(millisUntilFinished: Long) {
            _timer.postValue(timer.value?.minus(1))
        }

        override fun onFinish() {
            println("Zeit ist vorbei!")
        }
    }

    fun randomGame(){

        var randomInt= nextInt(0,SpielListe().beispiel.size)
        spiel1= mutableStateOf(listeSpiele.beispiel[randomInt])
        countDownTimer.start()

    }





}
