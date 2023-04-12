package com.mohammad.askar.weatherapp.features.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mohammad.askar.weatherapp.R
import com.mohammad.askar.weatherapp.features.presentation.bottomnavigation.BottomNavigationBar
import com.mohammad.askar.weatherapp.ui.theme.brightBlue
import com.ramcosta.composedestinations.spec.NavHostEngine
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    navHostEngine: NavHostEngine
) {

    val scale = remember {
        Animatable(0F)
    }

    var startSplashScreen = remember {
        mutableStateOf(true)
    }

    if (startSplashScreen.value) {

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.99F,
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = {
                        OvershootInterpolator(2F).getInterpolation(it)
                    }
                )
            )
            delay(2000L)
            startSplashScreen.value = false
        }
        Splash(scale = scale)
    } else {
        NavigateToNavigationBar(navController = navController, navHostEngine = navHostEngine)
    }

}


@Composable
fun NavigateToNavigationBar(
    navController: NavHostController,
    navHostEngine: NavHostEngine
) {
    BottomNavigationBar(navController = navController, navHostEngine = navHostEngine)
}

@Composable
fun Splash(scale: Animatable<Float, AnimationVector1D>) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brightBlue),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(240.dp)
                    .padding(8.dp)
                    .scale(scale = scale.value),
                painter = painterResource(
                    id = R.drawable.logo_splash_screen
                ),
                contentDescription = "logo",
            )

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .scale(scale = scale.value),
                text = "Created by\n MOHMMAD ASKAR",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                color = White,
                textAlign = TextAlign.Center,
            )
        }
    }
}
