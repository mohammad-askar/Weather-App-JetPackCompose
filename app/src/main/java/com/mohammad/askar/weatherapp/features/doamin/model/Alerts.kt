package com.mohammad.askar.weatherapp.features.doamin.model

import com.google.gson.annotations.SerializedName

data class Alerts(
    @SerializedName("alert")
    var alert : ArrayList<Alert> = arrayListOf()

)
