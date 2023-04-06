package com.mohammad.askar.weatherapp.features.doamin.model

import com.google.gson.annotations.SerializedName

data class Forecast(

    @SerializedName("forecastday")
    var forecastDay: ArrayList<ForecastDay> = arrayListOf()

)