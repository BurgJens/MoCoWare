package com.example.testrobert.sensor

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class LightSensor:Service(),SensorEventListener {


    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor


    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, "Start: Light Sensor ", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Stop: Light Sensor", Toast.LENGTH_SHORT).show()
        sensorManager.unregisterListener(this)

    }


    override fun onSensorChanged(event: SensorEvent?) {
        event?: return

        var lichtWert1=event.values[0]
        var lichtWert2=event.values[1]
        var lichtWert3=event.values[2]




        println(" ")
        println(lichtWert1)
        println(lichtWert2)
        println(lichtWert3)


        val intent = Intent("testLight")
        intent.putExtra("lightX", lichtWert1)
        intent.putExtra("lightY", lichtWert1)
        intent.putExtra("lightZ", lichtWert1)


        LocalBroadcastManager.getInstance(this@LightSensor).sendBroadcast(intent)


    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println("onAccuracyChanged")
        // Hier wird der Sensor freigegeben, wenn sinnvoller Wert verfügbar.
    }
}