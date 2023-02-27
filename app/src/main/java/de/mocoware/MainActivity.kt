package de.mocoware

import android.Manifest
import android.content.Context
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
import de.mocoware.ui.theme.MoCoWareTheme
import de.mocoware.util.*
import de.mocoware.view.navigation.AppNavigation
import de.mocoware.viewmodel.*
//import de.mocoware.view.AppNavigation

//Branch test hihi

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val joinGameViewModel: JoinGameViewModel by viewModels()
        val gameViewModel: GameViewModel by viewModels()
        val createGameViewModel: CreateGameViewModel by viewModels()
        val statisticsViewModel: StatisticsViewModel by viewModels()
        val userNameViewModel: UserNameViewModel by viewModels()

        userNameViewModel.getPlayername(this@MainActivity)



        // register receiver
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(gameViewModel.Receiver(), IntentFilter(SPEED_SENSOR))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(gameViewModel.Receiver(), IntentFilter(ACCELERATION_SENSOR))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(gameViewModel.Receiver(), IntentFilter(GYRO_SENSOR))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(gameViewModel.Receiver(), IntentFilter(LIGHT_SENSOR))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(gameViewModel.Receiver(), IntentFilter(ROTATION_VECTOR_SENSOR))

        setContent {
            MoCoWareTheme {
                AppNavigation(
                    joinGameViewModel = joinGameViewModel,
                    gameViewModel = gameViewModel,
                    createGameViewModel = createGameViewModel,
                    statisticsViewModel = statisticsViewModel,
                    userNameViewModel = userNameViewModel
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
