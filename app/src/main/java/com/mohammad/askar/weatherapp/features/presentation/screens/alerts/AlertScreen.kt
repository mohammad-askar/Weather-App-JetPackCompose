package com.mohammad.askar.weatherapp.features.presentation.screens.alerts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohammad.askar.weatherapp.features.doamin.model.Alert
import com.mohammad.askar.weatherapp.features.presentation.screens.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AlertScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            content = {
                if (state.data?.alerts?.alert.isNullOrEmpty()) {
                    item {

                        Text(
                            text = "There is No Alert Now!",
                            color = White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                val alerts: List<Alert> = state.data?.alerts?.alert ?: emptyList()
                items(alerts) { details ->

                    AlertItem(
                        severity = details.severity!!,
                        headline = details.headline!!
                    )
                }
            }
        )
    }
}