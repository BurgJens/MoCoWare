package com.example.testrobert

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testrobert.sensor.SpeedSensor

import com.example.testrobert.ui.theme.MoCoWareTheme

import java.util.*




class MainActivity : ComponentActivity() {

    private val CAMERA_REQUEST_CODE = 100

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)














        setupPermission()

        setContent {
            MoCoWareTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Start",

                    ) {
                    composable(route = "Start") {
                        StartScreen(navController)
                    }
                    composable(route = "Erstellen") {
                        val intent = Intent(this@MainActivity, SensorActivity::class.java)
                        startActivity(intent)

                    }
                    composable(route = "Beitreten") {
                        BeitretenScreen(navController)
                    }
                }
                // A surface container using the 'background' color from the theme
            }
        }
    }







    // Freigabe anfragen für Kamera
    private fun setupPermission() {

        val permissionCamera = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )
        val permissionGPS = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )

        val permissionBluetoot = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.BLUETOOTH
        )

        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }

        if (permissionBluetoot != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }

        if (permissionGPS != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }

    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(

                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION, // Andorid 10 or higher !!!!!
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            CAMERA_REQUEST_CODE
        )
    }

}


