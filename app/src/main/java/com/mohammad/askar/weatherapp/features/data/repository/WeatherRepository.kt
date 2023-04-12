package com.mohammad.askar.weatherapp.features.data.repository

import android.content.SharedPreferences
import com.mohammad.askar.weatherapp.features.utils.Constants.LOCATION_QUERY
import com.mohammad.askar.weatherapp.features.data.remote.ApiService
import com.mohammad.askar.weatherapp.features.doamin.model.WeatherResponse
import com.mohammad.askar.weatherapp.features.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.HttpException
import java.io.IOException

class WeatherRepository(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences

) {
    val currentLocationQuery = MutableStateFlow(
        sharedPreferences.getString(LOCATION_QUERY,"Trier"))

    suspend fun getWeatherData(location : String) : Resource<WeatherResponse> {

        return try {
            Resource.Success(
                data = api.getWeather(location)
            )
        }catch (e: IOException){
            Resource.Error(message = "Error! ${e.message}")
        }catch (e: HttpException){
            Resource.Error(
                message = "Error!${e.localizedMessage}"
            )
        }
    }


    fun saveToSharedPrefs(locationName: String) {
        sharedPreferences.edit().putString(LOCATION_QUERY, locationName).apply()
        currentLocationQuery.update { locationName }
    }
    fun deleteFromSharedPrefs(locationName: String) {
        sharedPreferences.edit().remove(LOCATION_QUERY).apply()
        currentLocationQuery.value = locationName
    }
}