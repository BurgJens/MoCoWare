package com.example.testrobert




import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.testrobert.view.screens.SplashScreen
import com.example.testrobert.viewmodel.SpielViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StartScreen(
    viewModel: SpielViewModel,
    navController: NavController,
    context: Context
) {


    viewModel.countDownTimer.cancel()
    viewModel.setTime(30)


    Permission(
        permissionNotAvailableContent = {
            Column(Modifier.fillMaxSize()) {
                Text("O noes! No Camera!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                    }
                ) {
                    Text("Open Settings")
                }
            }
        }
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column() {
                Button(
                    modifier = Modifier
                        .padding(20.dp),
                    onClick = {
                        navController.navigate(NavRoutes.Erstellen.route) // ändern wenn mehr screens erstellt

                    }) {
                    Text(text = "Erstellen")
                }
                Button(
                    modifier = Modifier
                        .padding(20.dp),
                    onClick = {
                        navController.navigate(NavRoutes.Beitreten.route) // ändern wenn mehr screens erstellt

                    }) {
                    Text(text = "Beitreten")
                }
            }
            // Box(){
            //     Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
            //     Text(text = "über dem Foro")
            // }

        }
    }

}









