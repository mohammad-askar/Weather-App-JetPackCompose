package com.mohammad.askar.weatherapp.features.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class LocationsEntity(
    val locationName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)