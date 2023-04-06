package com.mohammad.askar.weatherapp.features.data.local.mapper

import com.mohammad.askar.weatherapp.features.data.local.entity.LocationsEntity
import com.mohammad.askar.weatherapp.features.doamin.model.Locations

fun LocationsEntity.toLocations(): Locations {
    return Locations(
        locationName = locationName,
        id = id
    )
}

fun Locations.toLocationsEntity(): LocationsEntity {
    return LocationsEntity(
        locationName = locationName,
        id = id
    )
}