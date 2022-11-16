package com.example.testrobert.sensor

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlin.math.floor
import kotlin.math.roundToInt


class SpeedSensor:Service() {

    private var locationManager: LocationManager? = null


    inner class LocationListenerKlasse internal constructor(provider: String)   :LocationListener{

        private var lastLocation: Location
        private var speed = 0.0
        private var distance = 0.0
        private var curTime: Long = 0
        private var prevTime: Long = 0

        override fun onLocationChanged(location: Location) {
            println(speed)
            curTime = System.currentTimeMillis()

            /* distance : KM  ---  timeDiff : secs  ---  speed : KM/HR */
            distance = (location.distanceTo(lastLocation) / 1000).toDouble()
            var timeDiff = ((curTime - prevTime) / 1000.0f).toDouble()
            timeDiff = (timeDiff * 100.0).roundToInt() / 100.0
            Log.i(TAG, "onLocationChanged: TIMEDIFF: $timeDiff   DIST: $distance")
            speed = floor(distance / timeDiff * 3600 * 10) / 10
            lastLocation.set(location)
            prevTime = System.currentTimeMillis()
            lastLocation.set(location)
            val broadcastIntent = Intent()
            broadcastIntent.putExtra("speed", speed)
            broadcastIntent.action = "com.example.testRobert.UPDATE_SPEED"
            broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            sendBroadcast(broadcastIntent)
        }

        override fun onProviderDisabled(provider: String) {
            Log.e(TAG, "onProviderDisabled: $provider")
        }

        override fun onProviderEnabled(provider: String) {
            Log.e(TAG, "onProviderEnabled: $provider")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            Log.e(TAG, "onStatusChanged: $provider")
        }

        init {
            Log.e(TAG, "LocationListener $provider")
            lastLocation = Location(provider)
        }
    }

    private var locationListeners = arrayOf(
        LocationListenerKlasse(LocationManager.GPS_PROVIDER),
        LocationListenerKlasse(LocationManager.NETWORK_PROVIDER)
    )

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.e(TAG, "onCreate")
        initializeLocationManager()

        try {
            locationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                LOCATION_INTERVAL.toLong(),
                LOCATION_DISTANCE.toFloat(),
                locationListeners[0]
            )
        } catch (ex: SecurityException) {
            Log.i(TAG, "fail to request location update, ignore", ex)
        } catch (ex: IllegalArgumentException) {
            Log.d(TAG, "network provider does not exist, " + ex.message)
        }
    }


    private fun initializeLocationManager() {
        Log.e(
            TAG,
            "initializeLocationManager - LOCATION_INTERVAL: $LOCATION_INTERVAL LOCATION_DISTANCE: $LOCATION_DISTANCE"
        )
        if (locationManager == null) {
            locationManager =
                applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
        }
    }



    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        try {
            for (i in locationListeners){
                locationManager?.removeUpdates(i)
            }

        }catch (e:Exception){
            Log.i(TAG, "Konnte nicht gelöscht werden", e)
        }
        super.onDestroy()
    }


    companion object {
        private const val TAG = "LocationService"
        private const val LOCATION_INTERVAL = 1500
        private const val LOCATION_DISTANCE = 0
    }

}
