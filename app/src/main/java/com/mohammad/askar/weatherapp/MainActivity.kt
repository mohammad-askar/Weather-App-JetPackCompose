package com.mohammad.askar.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mohammad.askar.weatherapp.features.presentation.bottomnavigation.BottomNavigationBar
import com.mohammad.askar.weatherapp.features.presentation.screens.NavGraphs
import com.mohammad.askar.weatherapp.ui.theme.WeatherAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
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

                BottomNavigationBar(navController = navController) {
                    Box(modifier = Modifier.padding(it)) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                            engine = navHostEngine
                        )

                    }
                }
            }
        }
    }
}
