package com.mohammad.askar.weatherapp.features.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohammad.askar.weatherapp.features.data.local.entity.LocationsEntity

@Database(entities = [LocationsEntity::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {
    abstract val dao : LocationsDao
}