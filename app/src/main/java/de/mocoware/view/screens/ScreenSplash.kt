package de.mocoware.view.screens

import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import de.mocoware.R
import de.mocoware.model.PLAYERNAME_DEFAULT
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.view.elements.GenericButton
import de.mocoware.view.navigation.NavScreen
import de.mocoware.viewmodel.UserNameViewModel


@Composable
fun SplashScreenStart(navController: NavController, ) {
    val scale = remember {
        Animatable(0.2f)
    }

    LaunchedEffect(
        key1 = true,
        block = {
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
fun SplashScreenEnd(
    navController: NavController,
    viewModel: UserNameViewModel
) {
    val scale = remember {
        Animatable(15f)
    }

    var showButton by remember{ mutableStateOf(false)}

    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(targetValue = 2.0f,
                animationSpec = tween(
                    durationMillis = 200,
                )
            )
            delay(1000L)
            navController.popBackStack()
            showButton = true
        }
    )
    Box(
        modifier = Modifier
            .background(color = White)
            .scale(scale.value)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(painter = painterResource(id = R.drawable.splash3),
            contentDescription = "MocoWare",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(150.dp).offset(0.dp,-30.dp)
        )

        AnimatedVisibility(
            visible = showButton
        ) {
            GenericButton(
                offsetX = 0.dp,
                offsetY = 30.dp,
                text = "Start",
                color = Black,
                textColor = White,
                rotation = 0f
            ) {
                println("                                       " + viewModel.nameLive.value)
                if (viewModel.nameLive.value == PLAYERNAME_DEFAULT){
                    navController.navigate(NavScreen.EnterUserName.route)

                } else{
                    navController.navigate(NavScreen.Start.route)
                }
            }
        }
    }
}