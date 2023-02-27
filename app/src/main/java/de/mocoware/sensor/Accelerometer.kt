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
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import de.mocoware.util.ACCELERATION_SENSOR
import de.mocoware.util.ACCELERATION_SENSOR_X_VALUE
import de.mocoware.util.ACCELERATION_SENSOR_Y_VALUE
import de.mocoware.util.ACCELERATION_SENSOR_Z_VALUE
import java.lang.Math.abs


class Accelerometer : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorBeschleunigung: Sensor


    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorBeschleunigung = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        sensorManager.registerListener(this, sensorBeschleunigung, SensorManager.SENSOR_DELAY_NORMAL)

        Log.d("checkSensors","ACCELEROMETER CREATED")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return

        var axisX: Float = abs(event.values[0])
        var axisY: Float = abs(event.values[1])
        var axisZ: Float = abs(event.values[2])

        val intent = Intent(ACCELERATION_SENSOR)
        intent.putExtra(ACCELERATION_SENSOR_X_VALUE, axisX)
        intent.putExtra(ACCELERATION_SENSOR_Y_VALUE, axisY)
        intent.putExtra(ACCELERATION_SENSOR_Z_VALUE, axisZ)

        LocalBroadcastManager.getInstance(this@Accelerometer).sendBroadcast(intent)

//        Log.d("checkSensors","ACCELEROMETER CHANGED")
//        Log.d("checkSensors","$axisX   $axisY   $axisZ")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // wird aufgerufen wenn sich die Messgenauigkeit ändert
        println("onAccuracyChanged")
    }






}