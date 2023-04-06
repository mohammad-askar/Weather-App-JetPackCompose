package com.mohammad.askar.weatherapp.features.doamin.model
import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("location")
    var location: Location,
    @SerializedName("current")
    var current: Current? = Current(),
    @SerializedName("forecast")
    var forecast: Forecast? = Forecast(),
    @SerializedName("alerts")
    var alerts: Alerts? = Alerts()

)