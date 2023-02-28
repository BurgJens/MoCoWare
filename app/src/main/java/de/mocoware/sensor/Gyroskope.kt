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
import de.mocoware.util.GYRO_SENSOR
import de.mocoware.util.GYRO_SENSOR_X_VALUE
import de.mocoware.util.GYRO_SENSOR_Y_VALUE
import de.mocoware.util.GYRO_SENSOR_Z_VALUE

class Gyroskope: Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorBeschleunigung: Sensor


    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorBeschleunigung = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED)

        sensorManager.registerListener(
            this,
            sensorBeschleunigung,
            SensorManager.SENSOR_DELAY_NORMAL
        )
//        Log.d("checkSensors","GYRO CREATED")
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


        var axisX: Float = event.values[0]
        var axisY: Float = event.values[1]
        var axisZ: Float = event.values[2]

        val intent = Intent(GYRO_SENSOR)
        intent.putExtra(GYRO_SENSOR_X_VALUE, axisX)
        intent.putExtra(GYRO_SENSOR_Y_VALUE, axisY)
        intent.putExtra(GYRO_SENSOR_Z_VALUE, axisZ)

        LocalBroadcastManager.getInstance(this@Gyroskope).sendBroadcast(intent)

//        Log.d("checkSensors","GYROSCOPE CHANGED")
//        Log.d("checkSensors","$axisX   $axisY   $axisZ")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        println("onAccuracyChanged")
    }
}






