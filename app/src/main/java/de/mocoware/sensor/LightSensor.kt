package de.mocoware.sensor

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import de.mocoware.util.LIGHT_SENSOR
import de.mocoware.util.LIGHT_SENSOR_VALUE


class LightSensor:Service(),SensorEventListener {


    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor


    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        Log.d("checkSensors","LIGHT SENSOR CREATED")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?: return

        val lichtWert1=event.values[0]

        val intent = Intent(LIGHT_SENSOR)
        intent.putExtra(LIGHT_SENSOR_VALUE, lichtWert1)

        LocalBroadcastManager.getInstance(this@LightSensor).sendBroadcast(intent)

//        Log.d("checkSensors","LIGHT CHANGED")
//        Log.d("checkSensors","$lichtWert1")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // wird aufgerufen wenn sich die Messgenauigkeit ändert
        println("onAccuracyChanged")
    }
}