package com.mohammad.askar.weatherapp.features.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mohammad.askar.weatherapp.features.data.local.entity.LocationsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationsDao {
    @Insert
    suspend fun addLocation(location: LocationsEntity)

    @Delete
    suspend fun deleteLocation(location: LocationsEntity)

    @Query("SELECT * FROM location_table ORDER BY locationName ASC")
    fun getAllLocations(): Flow<List<LocationsEntity>>

    @Query("Delete from location_table")
    suspend fun deleteAllLocations()
}