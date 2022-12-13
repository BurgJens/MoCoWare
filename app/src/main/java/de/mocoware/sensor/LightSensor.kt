package de.mocoware.sensor

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
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
        intent.putExtra("lichtWert", lichtWert1)


        LocalBroadcastManager.getInstance(this@LightSensor).sendBroadcast(intent)




    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // wird aufgerufen wenn sich die Messgenauigkeit ändert
        println("onAccuracyChanged")
    }
}