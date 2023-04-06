package com.mohammad.askar.weatherapp.features.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mohammad.askar.weatherapp.features.data.local.LocationsDao
import com.mohammad.askar.weatherapp.features.data.local.mapper.toLocations
import com.mohammad.askar.weatherapp.features.data.local.mapper.toLocationsEntity
import com.mohammad.askar.weatherapp.features.doamin.model.Locations
import com.mohammad.askar.weatherapp.features.doamin.repository.LocationsRepository

class LocationsRepositoryImpl(
    private val dao: LocationsDao
) : LocationsRepository {
    override suspend fun addLocation(location: Locations) {
        dao.addLocation(location.toLocationsEntity())
    }

    override suspend fun deleteLocation(locations: Locations) {
        dao.deleteLocation(locations.toLocationsEntity())
    }

    /*override fun getAllLocations(): Flow<List<Locations>> {
        return dao.getAllLocations().map { locations ->
            locations.map { it.toLocations() }
        }
    }*/

    // Here to fix

    override fun getAllLocations(): LiveData<List<Locations>> {
        return dao.getAllLocations().map{ entity->
            entity.map {
                it.toLocations()
            }
        }
    }
}