package com.mohammad.askar.weatherapp.features.data.remote

import com.mohammad.askar.weatherapp.BuildConfig.API_KEY
import com.mohammad.askar.weatherapp.features.doamin.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //http://api.weatherapi.com/v1/forecast.json?key=ENTER_API_KEY&q=London&days=1&aqi=no&alerts=no

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q") query : String = "Trier",
        @Query("key") key : String = API_KEY,
        @Query("days") days : Int = 5,
        @Query("aqi") aqi : String = "no",
        @Query("alerts") alerts : String = "yes",
    ) : WeatherResponse
}