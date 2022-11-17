package com.example.testalex.Sensor

import android.bluetooth.BluetoothCodecConfig
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.material.circularreveal.CircularRevealHelper.STRATEGY
import pub.devrel.easypermissions.AppSettingsDialog
import kotlin.coroutines.jvm.internal.CompletedContinuation.context


class BTService {


    private fun startAdvertising() {
        val advertisingOptions: AdvertisingOptions = BluetoothCodecConfig.Builder().setStrategy(STRATEGY).build()
        Nearby.getConnectionsClient(context)
            .startAdvertising(
                getLocalUserName(), SERVICE_ID, connectionLifecycleCallback, advertisingOptions
            )
            .addOnSuccessListener { unused: Void? -> }
            .addOnFailureListener { e: Exception? -> }
    }

    private fun startDiscovery() {
        val discoveryOptions: DiscoveryOptions = AppSettingsDialog.Builder().setStrategy(STRATEGY).build()
        Nearby.getConnectionsClient(context)
            .startDiscovery(SERVICE_ID, endpointDiscoveryCallback, discoveryOptions)
            .addOnSuccessListener { unused: Void? -> }
            .addOnFailureListener { e: java.lang.Exception? -> }
    }

}
