package com.mohammad.askar.weatherapp.features.doamin.repository
import androidx.lifecycle.LiveData
import com.mohammad.askar.weatherapp.features.doamin.model.Locations

interface LocationsRepository {


    suspend fun addLocation(location: Locations)

    suspend fun deleteLocation(locations: Locations)

    fun getAllLocations() : LiveData<List<Locations>>
}