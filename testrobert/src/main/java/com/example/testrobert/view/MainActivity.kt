package com.example.testrobert

import android.Manifest
import android.app.ActivityManager
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testrobert.model.Spiel
import com.example.testrobert.sensor.Accelerometer
import com.example.testrobert.sensor.LightSensor
import com.example.testrobert.sensor.SpeedSensor
import com.example.testrobert.ui.theme.MoCoWareTheme
import com.example.testrobert.view.screens.BeitretenScreen
import com.example.testrobert.view.screens.ErstellenScreen
import com.example.testrobert.view.screens.GameScreen
import com.example.testrobert.view.screens.WartenScreen
import com.example.testrobert.viewmodel.SpielViewModel
import java.util.*


class MainActivity : ComponentActivity() {

    private val REQUEST_ENABLE_BT = 1000
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var bluetoothManager: BluetoothManager

    lateinit var spiel1: Spiel

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.getAdapter()
        premissionCheck()
        getLocation()



        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val viewModel: SpielViewModel = viewModel()


            LocalBroadcastManager.getInstance(this)
                .registerReceiver(Receiver(viewModel), IntentFilter("testSpeed"))
            LocalBroadcastManager.getInstance(this)
                .registerReceiver(Receiver(viewModel), IntentFilter("testAccel"))


            val iAccel = Intent(this@MainActivity, Accelerometer::class.java)
            val iSpeed = Intent(this@MainActivity, SpeedSensor::class.java)
            val iLight = Intent(this@MainActivity, LightSensor::class.java)

            MoCoWareTheme {


                NavHost(
                    navController = navController,
                    startDestination = "Start",
                ) {

                    composable(route = NavRoutes.Start.route) {
                        StartScreen(navController = navController, viewModel = viewModel)

                    }

                    composable(route = NavRoutes.Erstellen.route) {
                        ErstellenScreen(navController = navController)

                    }

                    composable(route = NavRoutes.GameScreen.route) {
                        GameScreen(
                            viewModel = viewModel,
                            spiel1,
                            navController,
                            { startService(iSpeed) },
                            { startService(iAccel) },
                            {startService(iLight)}
                        )

                    }

                    composable(route = NavRoutes.Warten.route) {
                        WartenScreen(
                            viewModel = viewModel,
                            spiel1,
                            navController,
                            { stopService(iSpeed) },
                            { stopService(iAccel) })

                    }

                    composable(route = NavRoutes.Beitreten.route) {
                        BeitretenScreen(
                            navController = navController,
                            viewModel.listeSpiele.beispiel,
                            onItemClicked = { spiel ->

                                spiel1 = spiel
                                navController.navigate(NavRoutes.GameScreen.route)

                            })
                    }

                }
                // A surface container using the 'background' color from the theme
            }
        }
    }


    val permissionsList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT
        )
    } else {
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
        )
    }

    fun premissionCheck() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    println(permissions)
                    if (it.value) {
                        Toast.makeText(this, "Permission Granted " + it.key, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            this,
                            "Permission permanently denied ,you can enable it by going to app setting",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        requestPermissionLauncher.launch(permissionsList.toTypedArray())
    }



    // NUR FÜR TEST AUF DEVICE DA KEIN GPS AKTIV
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        }
    }
}

sealed class NavRoutes(val route: String) {
    object Erstellen : NavRoutes("Erstellen")
    object Beitreten : NavRoutes("Beitreten")
    object GameScreen : NavRoutes("GameScreen")
    object Start : NavRoutes("Start")
    object Warten : NavRoutes("Warten")
}

