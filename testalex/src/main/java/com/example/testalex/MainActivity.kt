package com.example.testalex


import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
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
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testalex.Sensor.NearbyConnections
import com.example.testalex.databinding.ActivityMainBinding
import com.example.testalex.ui.theme.MoCoWareTheme
import com.google.android.gms.location.*
import com.google.android.gms.nearby.connection.Strategy


class MainActivity : ComponentActivity() {

    private val REQUEST_CODE_REQUIRED_PERMISSIONS = 1
    private lateinit var binding: ActivityMainBinding


    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setupPermission()
        setContent {
            //val location by applicationViewModel.getLocationLiveData().observeAsState()
            MoCoWareTheme {

            }
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Start",
                ) {
                    composable(route = "Nearby") {
                        NearbyConnections().startActivity()
                    }

                }
            }
        }





        @CallSuper
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            val errMsg = "Cannot start without required permissions"
            if (requestCode == REQUEST_CODE_REQUIRED_PERMISSIONS) {
                grantResults.forEach {
                    if (it == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show()
                        finish()
                        return
                    }
                }
                recreate()
            }
        }



    }






