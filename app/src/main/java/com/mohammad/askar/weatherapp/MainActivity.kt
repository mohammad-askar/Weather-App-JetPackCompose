package com.mohammad.askar.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mohammad.askar.weatherapp.features.presentation.screens.splash.SplashScreen
import com.mohammad.askar.weatherapp.ui.theme.WeatherAppTheme

import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val navHostEngine = rememberNavHostEngine()

                SplashScreen(
                    navController = navController,
                    navHostEngine = navHostEngine
                )
            }
        }
    }
}
