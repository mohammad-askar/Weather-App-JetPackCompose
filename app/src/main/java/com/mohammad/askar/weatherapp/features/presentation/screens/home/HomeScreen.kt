package com.mohammad.askar.weatherapp.features.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohammad.askar.weatherapp.features.presentation.screens.home.components.DetailsItem
import com.mohammad.askar.weatherapp.features.presentation.screens.home.components.DailyItem
import com.mohammad.askar.weatherapp.features.doamin.model.ForecastDay
import com.mohammad.askar.weatherapp.features.doamin.model.Locations
import com.mohammad.askar.weatherapp.features.presentation.screens.home.components.HourItem
import com.mohammad.askar.weatherapp.features.utils.dayOfTheWeek
import com.mohammad.askar.weatherapp.ui.theme.brightBlue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val context = LocalContext.current
    val locationDialog = remember { mutableStateOf(false) }
    val localLocations: List<Locations> =
        viewModel.allLocations.observeAsState().value ?: emptyList()


    /**
     * Remember to implement   delete a city
     */

    if (state.isLoading) {
        val indicatorSize = 44.dp
        val trackWidth: Dp = (indicatorSize * .1f)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brightBlue),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                strokeWidth = trackWidth,
                color = White,
                strokeCap = StrokeCap.Round
            )
        }
    }else{
        DisplayData(viewModel = viewModel)
    }

}

@Composable
fun DisplayData(viewModel: HomeViewModel) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val locationDialog = remember { mutableStateOf(false) }
    val localLocations: List<Locations> =
        viewModel.allLocations.observeAsState().value ?: emptyList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brightBlue),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow(
                contentPadding = PaddingValues(end = 8.dp)
            ) {
                items(localLocations) { location ->

                    Card(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .height(30.dp)
                            .clickable {
                                viewModel.saveToSharedPrefs(location.locationName)
                                Toast
                                    .makeText(
                                        context, "${location.locationName} set as Default",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            },
                        backgroundColor = if (location.locationName == viewModel.currentLocation.value) {
                            brightBlue
                        } else Gray,
                        elevation = 5.dp,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = location.locationName,
                                color = White
                            )
                            IconButton(onClick = {
                                GlobalScope.launch(Dispatchers.Main) {
                                    viewModel.deleteLocation(Locations(locationName = location.locationName))
                                }
                                Toast.makeText(
                                    context, "${location.locationName} Deleted..",
                                    Toast.LENGTH_LONG
                                ).show()
                            }) {

                                Icon(

                                    imageVector = Icons.Default.Delete,
                                    tint = White,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                }
            }


            Card(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .height(30.dp)
                    .clickable {
                        locationDialog.value = true
                    }
                    .background(brightBlue)
                    .width(30.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add City",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = White
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${state.data?.location?.name}, ${state.data?.location?.country} ",
                color = White,
                fontSize = 20.sp
            )


        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${state.data?.current?.tempC}${0x00B0.toChar()}",
                    style = MaterialTheme.typography.h2.merge(),
                    color = White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(
                            start = 18.dp,
                            end = 18.dp,
                        )
                )
                Text(
                    text = "${state.data?.current?.condition?.text}",
                    style = MaterialTheme.typography.body1.merge(),
                    color = White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(
                            start = 18.dp,
                            bottom = 8.dp
                        )
                )
            }

            Column(
                modifier = Modifier
                    .aspectRatio(2f)
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("https:${state.data?.current?.condition?.icon}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "${state.data?.current?.condition?.text}",
                    modifier = Modifier
                        .size(100.dp)
                )
            }


        }

        Column(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(10.dp)
                )
                .background(brightBlue)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DetailsItem(
                    text1 = "Feels Like",
                    textValue = "${state.data?.current?.feelslikeC}${0x00B0.toChar()}"
                )
                Spacer(modifier = Modifier.width(8.dp))
                DetailsItem(
                    text1 = "Wind Speed",
                    textValue = "${state.data?.current?.windKph} kp/h"
                )
                Spacer(modifier = Modifier.width(8.dp))
                DetailsItem(
                    text1 = "Pressure",
                    textValue = "${state.data?.current?.pressureMb} Mb"
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                DetailsItem(
                    text1 = "Humidity",
                    textValue = "${state.data?.current?.humidity}%"
                )
                Spacer(modifier = Modifier.width(8.dp))
                //val windDir = "${state.data?.current?.windDir}"
                DetailsItem(
                    text1 = "Wind direction",
                    textValue = "${state.data?.current?.windDir}"
                )
                Spacer(modifier = Modifier.width(8.dp))
                DetailsItem(
                    text1 = "Uv Index",
                    textValue = "${state.data?.current?.uv}"
                )

            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            content = {
                val hourForecast: List<ForecastDay> =
                    state.data?.forecast?.forecastDay ?: emptyList()
                items(hourForecast) {
                    it.hour.forEach { hour ->
                        HourItem(
                            icon = "https:${hour.condition?.icon}",
                            degrees = hour.tempC?.toFloat() ?: 0F,
                            time = hour.time!!,
                        )
                    }

                }
            }
        )

        Column(
            modifier = Modifier
                .size(height = 250.dp, width = 480.dp)
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 2.dp),
                content = {
                    val dailyForecast: List<ForecastDay> =
                        state.data?.forecast?.forecastDay ?: emptyList()
                    items(dailyForecast) { details ->
                        details.day.avgtempC?.toFloat()?.let { it1 ->
                            DailyItem(
                                day = dayOfTheWeek(details.date!!),
                                degrees = it1,
                                icon = "https:${details.day.condition?.icon}"
                            )
                        }
                    }
                })
        }

        if (locationDialog.value) {
            AlertDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onDismissRequest = { locationDialog.value = false },
                title = {
                    Text(
                        style = TextStyle(
                            color = White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        text = "Add a Location",
                        modifier = Modifier.padding(8.dp),
                    )
                },
                text = {
                    TextField(
                        value = viewModel.locationDialogValue.value,
                        onValueChange = {
                            viewModel.setLocationDialogValue(it)
                        },
                        textStyle = TextStyle(color = White),
                        placeholder = { Text(text = "Tier", color = White) },
                    )
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(brightBlue),
                        onClick = {
                            viewModel.addLocation()
                            locationDialog.value = false
                            Toast.makeText(context, "Locations added", Toast.LENGTH_LONG).show()
                        }
                    ) {
                        Text(text = "Add", color = White)
                    }
                },
                backgroundColor = brightBlue,
                contentColor = White,
                shape = RoundedCornerShape(12.dp)
            )


        }
    }
}
