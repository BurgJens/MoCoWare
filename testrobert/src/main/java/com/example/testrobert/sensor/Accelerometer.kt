package com.example.testrobert.sensor

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import androidx.compose.ui.hapticfeedback.HapticFeedbackType.Companion.values
import java.time.chrono.JapaneseEra.values



class Accelerometer :Service() {

    private  var sensorManager: SensorManager? = null



    inner class EventListenerKlasse internal constructor(provider:String) :SensorEventListener{





        override fun onSensorChanged(event: SensorEvent?) {
            TODO("Not yet implemented")
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            TODO("Not yet implemented")
        }


    }



    private val nanoSecTwoSec = 1.0f / 1000000000.0f
    private val deltaRotationVector = FloatArray(4) { 0f }
    private var timestamp: Float = 0f



    companion object{
        lateinit var sensorManager: SensorManager
        var gyroScopeSensor:Sensor?=null

    }


    // Service override ab hier
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        println("test")

        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroScopeSensor= sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }







}