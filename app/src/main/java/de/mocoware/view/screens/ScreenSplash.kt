package de.mocoware.view.screens

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
import de.mocoware.view.NavScreen
import kotlinx.coroutines.delay
import de.mocoware.R


@Composable
fun ScreenSplash(viewModel: ViewModel,
                    navController: NavController
) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 2f,
            animationSpec = tween(
                durationMillis = 1500,
            )
        )
        delay(2000L)
        navController.popBackStack()
        navController.navigate(NavScreen.Start.route)
    })

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
            Image(painter = painterResource(id = R.drawable.mocoware_logo),
                contentDescription = "MocoWare",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp))
            // Text(text = "MoCoWare",
            //     style = MaterialTheme.typography.h5,
            //     color = Color.Black)
        }
    }
}