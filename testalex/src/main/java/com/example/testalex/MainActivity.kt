package com.example.testalex

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.testalex.util.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.example.testalex.sensor.Accelerometer
import com.example.testalex.sensor.LightSensor
import com.example.testalex.sensor.SpeedSensor
import com.example.testalex.ui.theme.MoCoWareTheme
import com.example.testalex.view.AppNavigation
import com.example.testalex.viewmodel.CreateGameViewModel
import com.example.testalex.viewmodel.GameViewModel
import com.example.testalex.viewmodel.JoinGameViewModel
import com.example.testalex.viewmodel.TestViewModel


class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val joinGameViewModel: JoinGameViewModel by viewModels()
        val gameViewModel: GameViewModel by viewModels()
        val createGameViewModel: CreateGameViewModel by viewModels()
        val testViewModel: TestViewModel by viewModels()


        // register receiver
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(gameViewModel.Receiver(), IntentFilter("testSpeed"))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(gameViewModel.Receiver(), IntentFilter("testAccel"))



        setContent {

            MoCoWareTheme {
                AppNavigation(
                    joinGameViewModel = joinGameViewModel,
                    gameViewModel = gameViewModel,
                    createGameViewModel = createGameViewModel,
                    testViewModel = testViewModel,
                    context = this@MainActivity
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
