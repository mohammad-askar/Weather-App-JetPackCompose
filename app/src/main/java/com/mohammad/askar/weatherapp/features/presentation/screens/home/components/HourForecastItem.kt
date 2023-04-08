package com.mohammad.askar.weatherapp.features.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohammad.askar.weatherapp.features.utils.formatTime
import com.mohammad.askar.weatherapp.ui.theme.brightBlue

@Composable
fun HourItem(
    icon: String,
    time: String,
    degrees: Float
) {
    Card(
        modifier = Modifier
            .size(width = 90.dp, height = 140.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = brightBlue,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatTime(time),
                fontSize = 16.sp,
                color = White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(icon)
                    .crossfade(true)
                    .build(),
                contentDescription = "hour forecast $time,",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterHorizontally),
                alignment = Alignment.Center
            )
            Text(
                text = "${degrees}${0x00B0.toChar()}",
                fontSize = 16.sp,
                color = White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}