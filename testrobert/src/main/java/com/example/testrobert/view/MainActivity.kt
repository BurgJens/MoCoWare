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
import android.util.Log
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

    lateinit var spiel1: Spiel
    private  val CAMERA_PRE=100
    private  val GPS_PRE=1


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPremission(Manifest.permission.ACCESS_FINE_LOCATION,1)
        checkPremission(Manifest.permission.CAMERA,100)



        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val viewModel: SpielViewModel = viewModel()


            LocalBroadcastManager.getInstance(this)
                .registerReceiver(viewModel.Receiver(), IntentFilter("testSpeed"))
            LocalBroadcastManager.getInstance(this)
                .registerReceiver(viewModel.Receiver(), IntentFilter("testAccel"))


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
                            { startService(iLight) }
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


    fun checkPremission(premisson:String,requestCode:Int){

        if(ContextCompat.checkSelfPermission(this,premisson)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(premisson),requestCode)
        }else{
            Toast.makeText(this,"Permission ist granted",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== GPS_PRE){
            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Location permission Granted",Toast.LENGTH_SHORT).show()
                this.recreate()
            }else{
                Toast.makeText(this,"Location permission Denied",Toast.LENGTH_SHORT).show()
            }
        }else if(requestCode== CAMERA_PRE){
            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Camera permission Granted",Toast.LENGTH_SHORT).show()
                this.recreate()
            }else{
                Toast.makeText(this,"Camera permission Denied",Toast.LENGTH_SHORT).show()
            }
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