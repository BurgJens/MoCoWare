package com.example.testalex.view.screens

import android.graphics.Color
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.testalex.view.NavScreen
import kotlinx.coroutines.delay
import com.example.testalex.R




@Composable
fun SplashScreenStart(navController: NavController, ) {
    val scale = remember {
        Animatable(0.2f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 10f,
            animationSpec = tween(
                durationMillis = 500,
            )
        )

        navController.popBackStack()
        navController.navigate(NavScreen.SplashEnd.route)

    }
    )

    Box(
        modifier = Modifier
            .background(color = White)
            .scale(scale.value)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = R.drawable.splash1),
                contentDescription = "MocoWare",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp))
        }
    }
}

@Composable
fun SplashScreenEnd(navController: NavController) {
    val scale = remember {
        Animatable(15f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 2.0f,
            animationSpec = tween(
                durationMillis = 200,
            )
        )
        delay(1500L)
        navController.popBackStack()
        navController.navigate(NavScreen.Start.route)
    }
    )
    Box(
        modifier = Modifier
            .background(color = White)
            .scale(scale.value)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = R.drawable.splash3),
                contentDescription = "MocoWare",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp))
        }
    }
}