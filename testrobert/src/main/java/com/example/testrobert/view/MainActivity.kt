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
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Popup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import androidx.navigation.compose.*
import com.example.testrobert.model.Spiel
import com.example.testrobert.sensor.Accelerometer
import com.example.testrobert.sensor.LightSensor
import com.example.testrobert.sensor.SpeedSensor
import com.example.testrobert.ui.theme.MoCoWareTheme
import com.example.testrobert.view.screens.*
import com.example.testrobert.viewmodel.SpielViewModel
import java.util.*


class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            var viewModel: SpielViewModel = viewModel()

            LocalBroadcastManager.getInstance(this)
                .registerReceiver(viewModel.Receiver(), IntentFilter("testSpeed"))
            LocalBroadcastManager.getInstance(this)
                .registerReceiver(viewModel.Receiver(), IntentFilter("testAccel"))
            LocalBroadcastManager.getInstance(this)
                .registerReceiver(viewModel.Receiver(), IntentFilter("testLight"))



            MoCoWareTheme {
                NavHost(
                    navController = navController,
                    startDestination = "Splash",
                ) {
                    composable(route = NavRoutes.Splash.route) {
                        SplashScreenStart(navController = navController)
                    }
                    composable(route = NavRoutes.SplashEnd.route) {
                        SplashScreenEnd(navController = navController)
                    }
                    composable(route = NavRoutes.Start.route) {

                        StartScreen(navController = navController,
                            viewModel = viewModel,
                            context = this@MainActivity
                        )
                    }

                    composable(route = NavRoutes.Erstellen.route) {

                        ErstellenScreen(
                            navController = navController
                        )
                    }

                    composable(route = NavRoutes.GameScreen.route) {

                        GameScreen(
                            viewModel = viewModel,
                            navController = navController,
                            context = this@MainActivity,
                        )
                        BackHandler {
                            navController.navigate(NavRoutes.SpeilVerlassen.route)
                        }
                    }

                    composable(route = NavRoutes.Warten.route) {

                        WartenScreen(
                            viewModel = viewModel,
                            navController,
                            context = this@MainActivity)
                    }

                    composable(route = NavRoutes.Beitreten.route) {

                        BeitretenScreen(
                            navController = navController,
                            viewModel.listeSpiele.beispiel,
                            onItemClicked = { spiel ->

                                viewModel.setTime(30)
                                viewModel.countDownTimer.start()
                                viewModel.spiel1 = mutableStateOf(spiel)

                                navController.navigate(NavRoutes.GameScreen.route)
                            })
                    }

                    composable(route=NavRoutes.SpeilVerlassen.route){

                        QuestionScreen(
                            navController = navController,
                            viewModel = viewModel,
                            context = this@MainActivity
                        )
                    }
                }
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
    object SpeilVerlassen : NavRoutes("SpielVerlassen?")
    object Splash : NavRoutes("Splash")
    object SplashEnd : NavRoutes("SplashEnd")
}