package com.mohammad.askar.weatherapp.features.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammad.askar.weatherapp.features.data.repository.WeatherRepository
import com.mohammad.askar.weatherapp.features.doamin.model.Locations
import com.mohammad.askar.weatherapp.features.doamin.repository.LocationsRepository
import com.mohammad.askar.weatherapp.features.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepository,
    private val locationRepo: LocationsRepository
) : ViewModel() {

    private val _locationDialogValue = MutableStateFlow("")
    val locationDialogValue = _locationDialogValue.asStateFlow()

    private val _currentLocation = MutableStateFlow("")
    val currentLocation = _currentLocation.asStateFlow()

    val allLocations = locationRepo.getAllLocations()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private fun notifyCurrentLocation(): StateFlow<String?> = weatherRepo.currentLocationQuery


    init {

//        getWeatherDetails()
        viewModelScope.launch {
            weatherRepo.currentLocationQuery.collect {
                _currentLocation.value = it!!
            }
        }
        viewModelScope.launch {
            notifyCurrentLocation().collect { location ->
                location?.let { getWeatherDetails(it) }
            }
        }
    }


        fun getWeatherDetails(location: String = "Trier") {
            viewModelScope.launch {
                _state.update { state.value.copy(isLoading = true) }
                when (val result = weatherRepo.getWeatherData(location)) {
                    is Resource.Success -> {
                        _state.update { state.value.copy(
                            data = result.data,
                            isLoading = false
                        ) }
                    }
                    is Resource.Error -> {
                        _state.update { state.value.copy(isLoading = true) }
                    }

                    else -> {}
                }

            }
            weatherRepo.saveToSharedPrefs(location)
        }

        fun saveToSharedPrefs(locationName: String) {
            weatherRepo.saveToSharedPrefs(locationName)
        }

        fun setLocationDialogValue(text: String) {
            _locationDialogValue.update { text }
        }

        fun deleteLocation(location: Locations) {
            viewModelScope.launch(Dispatchers.Main) {
                locationRepo.deleteLocation(location)
            }
            getWeatherDetails()
        }

        fun addLocation() {
            viewModelScope.launch {
                if (locationDialogValue.value.isNotEmpty()) {
                    locationRepo.addLocation(
                        Locations(
                            locationDialogValue.value
                        )
                    )
                } else return@launch
            }
        }

}
