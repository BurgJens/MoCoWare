package com.example.testrobert

import android.content.Context
import android.content.Intent
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testrobert.sensor.Accelerometer
import com.example.testrobert.sensor.SpeedSensor

import com.example.testrobert.ui.theme.MoCoWareTheme
import com.example.testrobert.view.CameraActivity
import com.example.testrobert.viewmodel.Playground

import java.util.*





class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var i =Intent(this,SpeedSensor::class.java)


        setContent {

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            MoCoWareTheme {



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
                        ErstellenScreen(navController = navController,{startService(i)},{stopService(i)})

                    }

                    composable(route = NavRoutes.Beitreten.route) {

                        BeitretenScreen(navController = navController)
                    }


                    composable(route=NavRoutes.Stoppen.route){

                        ErstellenScreen(navController = navController,{startService(i)},{stopService(i)})

                    }
                }
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

sealed class NavRoutes(val route: String) {
    object Erstellen : NavRoutes("erstellen")
    object Beitreten : NavRoutes("beitreten")
    object Start : NavRoutes("Start")
    object Stoppen : NavRoutes("Stoppen")
}

