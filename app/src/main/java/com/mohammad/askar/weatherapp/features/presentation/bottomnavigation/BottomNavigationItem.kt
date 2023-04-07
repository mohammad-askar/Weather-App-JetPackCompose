package com.mohammad.askar.weatherapp.features.presentation.bottomnavigation
import com.mohammad.askar.weatherapp.R
import com.mohammad.askar.weatherapp.features.presentation.screens.destinations.AlertScreenDestination
import com.mohammad.askar.weatherapp.features.presentation.screens.destinations.Destination
import com.mohammad.askar.weatherapp.features.presentation.screens.destinations.HomeScreenDestination
import com.mohammad.askar.weatherapp.features.presentation.screens.destinations.SearchScreenDestination


sealed class BottomNavigationItem(
    var title: String,
    var icon: Int,
    var destination: Destination
    ) {
    object Home : BottomNavigationItem("Home", R.drawable.ic_baseline_home_24, HomeScreenDestination)
    object Search : BottomNavigationItem("Search", R.drawable.ic_baseline_search_24, SearchScreenDestination)
    object Alert : BottomNavigationItem("Alerts", R.drawable.ic_alerts, AlertScreenDestination)
}
