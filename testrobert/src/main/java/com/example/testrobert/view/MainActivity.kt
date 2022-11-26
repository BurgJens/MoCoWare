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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testrobert.model.GameRoom
import com.example.testrobert.ui.theme.MoCoWareTheme
import com.example.testrobert.view.GameActivity
import com.example.testrobert.view.screens.BeitretenScreen
import com.example.testrobert.view.screens.ErstellenScreen
import com.example.testrobert.viewmodel.Playground
import java.util.*


class MainActivity : ComponentActivity() {

    private val REQUEST_ENABLE_BT = 1000
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var bluetoothManager: BluetoothManager


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

            MoCoWareTheme {

                val viewModel:Playground= viewModel()


                NavHost(
                    navController = navController,
                    startDestination = "Start",

                    ) {

                    composable(route = NavRoutes.Start.route) {
                        StartScreen(navController = navController,
                            )
                    }

                    composable(route = NavRoutes.Erstellen.route) {
                      //  startService(Intent(this@MainActivity,com.example.testrobert.sensor.SpeedSensor.javaClass))
                        ErstellenScreen(navController = navController)
                    }

                    composable(route = NavRoutes.Beitreten.route) {
                        BeitretenScreen(navController = navController, listOf(GameRoom("test"),GameRoom("test3")), onItemClicked = { spiel ->
                            //discuss if this should be view, viewmodel or model related code:


                            val intent = Intent(this@MainActivity,GameActivity::class.java)
                            startActivity(intent)
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
    object Start : NavRoutes("Start")
    object Stoppen : NavRoutes("Stoppen")
}

