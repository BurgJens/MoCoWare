package com.example.testalex.Sensor

/*import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.IBinder
import androidx.core.app.ActivityCompat
import com.example.testalex.Manifest
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.ConnectionsClient
import com.google.android.gms.nearby.connection.Strategy
import java.util.EventListener

class Bluetooth : Service(), SensorEventListener {

    var mRemoteEndpointId: String? = null
    lateinit var mConnectionsClient: ConnectionsClient

    companion object{
        private const val REQUEST_CODE_PERMISSIONS = 1
    }

    override fun onCreate() {
        super.onCreate()
        mConnectionsClient = Nearby.getConnectionsClient(this)
    }

    fun onStop(){
        mConnectionsClient.stopAdvertising()
        mConnectionsClient.stopDiscovery()
        mConnectionsClient.stopAllEndpoints()
    }
    private fun startAdvertising(){
        mConnectionsClient.startAdvertising(
            getNickName(),
            mConnectionsLifecycleCallback,
            Strategy.P2P_CLUSTER
        )
            .addOnSuccesListener{
                debug("Success startAdvertising: $it")
            }
            .addOnFailureListener{
                debug("Failure startDiscovery. $it")
            }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}*/