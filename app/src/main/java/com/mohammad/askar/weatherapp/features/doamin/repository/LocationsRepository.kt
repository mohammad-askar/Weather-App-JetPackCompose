package com.mohammad.askar.weatherapp.features.doamin.repository
import androidx.lifecycle.LiveData
import com.mohammad.askar.weatherapp.features.doamin.model.Locations
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {


    suspend fun addLocation(location: Locations = Locations(locationName = "Trier", 1))

    suspend fun deleteLocation(locations: Locations)

    suspend fun deleteAllLocations()

    fun getAllLocations() : Flow<List<Locations>>
}