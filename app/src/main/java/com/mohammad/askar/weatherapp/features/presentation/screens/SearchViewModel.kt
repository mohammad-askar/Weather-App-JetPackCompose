package com.mohammad.askar.weatherapp.features.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammad.askar.weatherapp.features.presentation.screens.home.HomeState
import com.mohammad.askar.weatherapp.features.data.repository.WeatherRepository
import com.mohammad.askar.weatherapp.features.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel(){
    private val _query = mutableStateOf("")
    val query: State<String> = _query

    fun setSearchQuery(location: String){
        search(location)
    }

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state


    fun search(location: String){
        _query.value = location

        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = repository.getWeatherData(_query.value)
            when(result){
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        data = result.data
                    )
                }
                is Resource.Error-> {
                    _state.value = state.value.copy(
                        isLoading = true,
                    )
                }
                else -> {

                }
            }
        }
    }

}