package com.mohammad.askar.weatherapp.features.doamin.model

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("date_epoch")
    var dateEpoch: Int? = null,
    @SerializedName("day")
    var day: Day,
    @SerializedName("hour")
    var hour: ArrayList<Hour> = arrayListOf()
)