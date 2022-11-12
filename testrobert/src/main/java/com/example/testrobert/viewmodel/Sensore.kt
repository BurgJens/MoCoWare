package com.example.testrobert.ui.theme

import android.content.ContentValues
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Half
import android.util.Log
import com.example.testrobert.MyViewModel
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Sensore: SensorEventListener {



    // Create a constant to convert nanoseconds to seconds.
    private val nanoSecTwoSec = 1.0f / 1000000000.0f
    private val deltaRotationVector = FloatArray(4) { 0f }
    private var timestamp: Float = 0f


    companion object{
        private lateinit var sensorManager: SensorManager
        private var gyroScopeSensor: Sensor? = null
        val myViewModel = MyViewModel()

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        if (timestamp != 0f) {
            val dT = (event.timestamp - timestamp) * nanoSecTwoSec
            // Axis of the rotation sample, not normalized yet.

            var axisX: Float = event.values[0]
            var axisY: Float = event.values[1]
            var axisZ: Float = event.values[2]



            // Calculate the angular speed of the sample
            val omegaMagnitude: Float = sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ)

            // Normalize the rotation vector if it's big enough to get the axis`enter code here`
            // (that is, EPSILON should represent your maximum allowable margin of error)
            if (omegaMagnitude > Half.EPSILON) {
                axisX /= omegaMagnitude
                axisY /= omegaMagnitude
                axisZ /= omegaMagnitude
            }
            val thetaOverTwo: Float = omegaMagnitude * dT / 2.0f
            val sinThetaOverTwo: Float = sin(thetaOverTwo)
            val cosThetaOverTwo: Float = cos(thetaOverTwo)

            deltaRotationVector[0] = sinThetaOverTwo * axisX
            deltaRotationVector[1] = sinThetaOverTwo * axisY
            deltaRotationVector[2] = sinThetaOverTwo * axisZ
            deltaRotationVector[3] = cosThetaOverTwo

        }
        timestamp = event.timestamp.toFloat()
        val deltaRotationMatrix = FloatArray(9) { 0f }
        SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);

        // User code should concatenate the delta rotation we computed with the current rotation
        // in order to get the updated rotation.
        // rotationCurrent = rotationCurrent * deltaRotationMatrix;
    }

    override fun onAccuracyChanged(param0: Sensor?, p1: Int) {
        Log.d(ContentValues.TAG, "onAccuracyChanged ${param0?.name}: $p1")
    }
}