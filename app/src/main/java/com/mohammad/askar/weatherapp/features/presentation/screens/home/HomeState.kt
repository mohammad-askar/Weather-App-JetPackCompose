package com.mohammad.askar.weatherapp.features.presentation.screens.home

import com.mohammad.askar.weatherapp.features.doamin.model.WeatherResponse

data class HomeState(
    val isLoading : Boolean = false,
    val data: WeatherResponse? = null
)

//sealed class HomeState(){
//    object Loading: HomeState()
//    class Success(val data: WeatherResponse): HomeState()
//    class Error(val error: String): HomeState()
//}