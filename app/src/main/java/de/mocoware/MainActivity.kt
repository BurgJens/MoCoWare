package de.mocoware

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
import com.example.testrobert.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.LightSensor
import de.mocoware.sensor.SpeedSensor
import de.mocoware.ui.theme.MoCoWareTheme
import de.mocoware.view.AppNavigation
import de.mocoware.viewmodel.GameViewModel
import de.mocoware.viewmodel.JoinGameViewModel


class MainActivity : ComponentActivity() {



    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        val joinGameViewModel: JoinGameViewModel by viewModels()
        val gameViewModel: GameViewModel by viewModels()
        val serviceObject =SerivceSystem()

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
                    serviceObject
                )
            }
        }
    }


    inner class SerivceSystem{
        var lichtIstActive:Boolean=false
        var beschleuIstActive:Boolean=false
        var speedIstActive:Boolean=false

        val intentAccel = Intent(this@MainActivity, Accelerometer::class.java)
        val intentSpeed = Intent(this@MainActivity, SpeedSensor::class.java)
        val intentLight = Intent(this@MainActivity, LightSensor::class.java)

        fun startLicht(){
            if (!lichtIstActive) {
                startService(intentLight)
                lichtIstActive=true
            }
        }
        fun startBeschleu(){
            if(!beschleuIstActive) {
                beschleuIstActive=true
                startService(intentAccel)
            }
        }
        fun startSpeed(){
            if (!speedIstActive){
                startService(intentSpeed)
                speedIstActive=true
            }
        }
        fun stopLicht(){
            if (lichtIstActive){
                stopService(intentLight)
                lichtIstActive=false
            }
        }
        fun stopBeschleu(){
            if(beschleuIstActive) {
                stopService(intentAccel)
                beschleuIstActive=false
            }
        }
        fun stopSpeed(){
            if (speedIstActive){
                stopService(intentSpeed)
                speedIstActive=false
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
