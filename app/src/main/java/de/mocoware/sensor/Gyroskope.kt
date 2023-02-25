package de.mocoware.sensor

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


        val intent = Intent("Gyro")
        intent.putExtra("axisXGyro", axisX)
        intent.putExtra("axisYGyro", axisY)
        intent.putExtra("axisZGyro", axisZ)

        LocalBroadcastManager.getInstance(this@Gyroskope).sendBroadcast(intent)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // wird aufgerufen wenn sich die Messgenauigkeit ändert
        println("onAccuracyChanged")
    }
}






