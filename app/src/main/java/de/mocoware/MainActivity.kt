package de.mocoware

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import de.mocoware.receiver.Receiver
import de.mocoware.sensor.Accelerometer
import de.mocoware.sensor.LightSensor
import de.mocoware.sensor.SpeedSensor
import de.mocoware.ui.theme.MoCoWareTheme
import de.mocoware.view.AppNavigation
import de.mocoware.viewmodel.GameViewModel
import de.mocoware.viewmodel.JoinGameViewModel


class MainActivity : ComponentActivity() {

    private val CAMERA_PRE=100
    private val GPS_PRE=1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        checkPremission(Manifest.permission.CAMERA,100)
        checkPremission(Manifest.permission.ACCESS_FINE_LOCATION,1)

        val joinGameViewModel: JoinGameViewModel by viewModels()
        val gameViewModel: GameViewModel by viewModels()
        val serviceObject =SerivceSystem()

        // register receiver
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(Receiver(gameViewModel), IntentFilter("testSpeed"))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(Receiver(gameViewModel), IntentFilter("testAccel"))



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




    // Runtime permission function under here
    fun checkPremission(premisson:String,requestCode:Int){

        if(ContextCompat.checkSelfPermission(this,premisson)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(premisson),requestCode)
        }else{
            if (premisson==Manifest.permission.CAMERA){
                Toast.makeText(this,"Camera has premission", Toast.LENGTH_SHORT).show()
            }
            if (premisson==Manifest.permission.ACCESS_FINE_LOCATION){
                Toast.makeText(this,"Location has premission", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== GPS_PRE){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Location permission Granted", Toast.LENGTH_SHORT).show()
                this.recreate()
            }else{
                Toast.makeText(this,"Location permission Denied", Toast.LENGTH_SHORT).show()

            }
        }else if(requestCode== CAMERA_PRE){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Camera permission Granted", Toast.LENGTH_SHORT).show()
                this.recreate()
            }else{
                Toast.makeText(this,"Camera permission Denied", Toast.LENGTH_SHORT).show()


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
