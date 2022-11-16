package com.example.testalex


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.GpsStatus
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.telephony.CarrierConfigManager.Gps
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testalex.dto.LocationDetails
import com.example.testalex.ui.theme.MoCoWareTheme
import com.google.android.gms.location.*
import javax.crypto.spec.GCMParameterSpec


class MainActivity : ComponentActivity(){


    private val CAMERA_REQUEST_CODE = 100
    //private val applicationViewModel: ApplicationViewModel by viewModel<ApplicationViewModel>()

    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setupPermission()
        setContent {
            //val location by applicationViewModel.getLocationLiveData().observeAsState()
            MoCoWareTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Start",
                ) {
                    composable(route = "Start") {
                        StartScreen(navController)
                    }
                    composable(route = "Erstellen") {
                        prepLocationUpdates()
                    }
                    composable(route = "Beitreten") {
                        Beitreten(navController)
                    }
                }
            }
        }
    }

    private fun prepLocationUpdates() {
       if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {
           requestLocationUpdates()
    }    else{
        requestSinglePermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

}
    private val requestSinglePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isGranted ->
        if(isGranted){
            requestLocationUpdates()
        }else{
            Toast.makeText(this,"GPS Unavailable",Toast.LENGTH_LONG).show()
        }
    }



    private fun requestLocationUpdates() {
        applicationViewModel.startLocationUpdates()
    }

    @Composable
    fun StartScreen(
        navController: NavController
    ) {

        BoxWithConstraints {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                PressIconButton(
                    onClick = { navController.navigate("Erstellen") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                    text = { Text("Erstellen") }


                )
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }
        }
    }

    @Composable
    fun PressIconButton(
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        text: @Composable () -> Unit,
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource =
            remember { MutableInteractionSource() },
    ) {


        val isPressed by interactionSource.collectIsPressedAsState()
        val color = if (isPressed) Color.Green else Color.Blue
        Button(
            onClick = onClick, modifier = modifier,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            AnimatedVisibility(visible = isPressed) {
                if (isPressed) {
                    Row {
                        icon()
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))


                    }
                }
            }
            text()
        }
    }

    @Composable
    fun Erstellen(
        navController: NavController,
        location: LocationDetails?
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            GPS(location)
        }
    }

@Composable
fun GPS(location: LocationDetails?) {
    location?.let{
        Text(text = location.Latitude)
        Text(text = location.longitude)
    }

}


//        Column (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(6.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Aktuelle Position")
//
//
//        }
//    }


    @Composable
    fun Beitreten(
        navController: NavController
    ) {
        val padding = 16.dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Image(

                    painter = painterResource(id = R.drawable.huepfen),
                    contentDescription = null,
                )
                Spacer(Modifier.size(padding))
                Column {
                    Text("Lobby 1")
                    Text("3 Spieler")
                }
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }
            Row() {
                Image(

                    painter = painterResource(id = R.drawable.huepfen),
                    contentDescription = null,
                )
                Spacer(Modifier.size(padding))
                Column {
                    Text("Lobby 2")
                    Text("1 Spieler")
                }
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }
            Row() {
                Image(

                    painter = painterResource(id = R.drawable.huepfen),
                    contentDescription = null,
                )
                Spacer(Modifier.size(padding))
                Column {
                    Text("Lobby 3")
                    Text("14 Spieler")
                }
                PressIconButton(
                    onClick = { navController.navigate("Beitreten") },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    text = { Text("Beitreten") }

                )

            }


        }
    }



/*// Freigabe anfragen für Kamera
private fun setupPermission() {

    val permissionCamera = ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.CAMERA,
    )
    val permissionGPS = ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
    )

    if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
        makeRequest()
    }

    if (permissionGPS != PackageManager.PERMISSION_GRANTED) {
        makeRequest()
    }

}

private fun makeRequest() {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION // Andorid 10 or higher !!!!!
        ),
        CAMERA_REQUEST_CODE
    )
}
}
*/