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
import de.mocoware.util.*

class RotationVector : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var rotationSensor: Sensor

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    interface OrientationListener {
        fun onOrientationChanged(roll: Float, pitch: Float, azimuth: Float)
    }

    private var orientationListener: OrientationListener? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        Log.d("checkSensors","ROTATION VEKTOR CREATED")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL)
        return START_STICKY
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }


    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            SensorManager.getOrientation(rotationMatrix, orientationAngles)

            val roll = Math.toDegrees(orientationAngles[2].toDouble()).toFloat()
            val pitch = Math.toDegrees(orientationAngles[1].toDouble()).toFloat()
            val yaw = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()

            val intent = Intent(ROTATION_VECTOR_SENSOR)
            intent.putExtra(ROTATION_VECTOR_ROLL_VALUE, roll)
            intent.putExtra(ROTATION_VECTOR_PITCH_VALUE, pitch)
            intent.putExtra(ROTATION_VECTOR_YAW_VALUE, yaw)

            LocalBroadcastManager.getInstance(this@RotationVector).sendBroadcast(intent)

//            Log.d("checkSensors","ROTATION VECTOR CHANGED")
//            Log.d("checkSensors","$roll   $pitch   $yaw")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Nicht relevant für dieses Beispiel
    }
}