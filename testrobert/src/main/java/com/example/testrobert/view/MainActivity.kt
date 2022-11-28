package com.example.testrobert

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.ObserverHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testrobert.model.Spiel
import com.example.testrobert.model.SpielListe
import com.example.testrobert.sensor.Accelerometer
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


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.getAdapter()
        premissionCheck()



        setContent {

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val viewModel:SpielViewModel= viewModel()


            var iAccel =Intent(this@MainActivity,Accelerometer::class.java)
            var iSpeed =Intent(this@MainActivity,SpeedSensor::class.java)

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
                        GameScreen(viewModel = viewModel,spiel1,navController)

                        if(spiel1.name=="Shake") startService(iAccel)
                        if(spiel1.name=="Laufen")startService(iSpeed)

                    }

                    composable(route = NavRoutes.Warten.route) {
                        WartenScreen(viewModel = viewModel,spiel1,navController)
                        stopService(iAccel)
                        stopService(iSpeed)

                    }

                    composable(route = NavRoutes.Beitreten.route) {
                        BeitretenScreen(navController = navController, viewModel.listeSpiele.beispiel, onItemClicked = { spiel ->

                            spiel1=spiel


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
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    } else {
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    }

    fun premissionCheck(){
        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                println(permissions)
                if (it.value) {
                    Toast.makeText(this, "Permission Granted ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission permanently denied ,you can enable it by going to app setting", Toast.LENGTH_SHORT).show()
                }
            }
        }
        requestPermissionLauncher.launch(permissionsList.toTypedArray())
    }



}

sealed class NavRoutes(val route: String) {
    object Erstellen : NavRoutes("Erstellen")
    object Beitreten : NavRoutes("Beitreten")
    object GameScreen : NavRoutes("GameScreen")
    object Start : NavRoutes("Start")
    object Warten : NavRoutes("Warten")
}

