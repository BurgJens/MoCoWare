package de.testjens.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.testjens.view.screens.ScreenBeitreten
import de.testjens.view.screens.ScreenStart
import de.testjens.viewmodel.AppViewModel

@Composable
fun AppNavigation(viewModel: AppViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "start"){
        composable("start"){
            ScreenStart(navController = navController, viewModel = viewModel)
        }
        composable("joinGame"){
            ScreenBeitreten(navController = navController, viewModel =viewModel)
        }
    }
}