package de.mocoware

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import de.mocoware.ui.theme.MoCoWareTheme
import de.mocoware.view.AppNavigation
import de.mocoware.viewmodel.GameViewModel
import de.mocoware.viewmodel.JoinGameViewModel


class MainActivity : ComponentActivity() {

    private  val CAMERA_PRE=100
    private  val GPS_PRE=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPremission(Manifest.permission.CAMERA,100)
        checkPremission(Manifest.permission.ACCESS_FINE_LOCATION,2)



        val joinGameViewModel: JoinGameViewModel by viewModels()
        val gameViewModel: GameViewModel by viewModels()

        setContent {
            MoCoWareTheme {
                AppNavigation(
                    joinGameViewModel = joinGameViewModel,
                    gameViewModel = gameViewModel
                )
            }
        }
    }

    fun checkPremission(premisson:String,requestCode:Int){

        if(ContextCompat.checkSelfPermission(this,premisson)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(premisson),requestCode)
        }else{
            Toast.makeText(this,"Permission ist granted", Toast.LENGTH_SHORT).show()
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
