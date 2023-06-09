package com.mohammad.askar.weatherapp.features.presentation.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohammad.askar.weatherapp.ui.theme.DailyItemBackground

@Composable
fun DailyItem(
    day: String,
    degrees: Float,
    icon: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DailyItemBackground,
        elevation = 5.dp
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.align(CenterVertically),
                text = day,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                textAlign = TextAlign.Start
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(icon)
                    .crossfade(true)
                    .build(),
                contentDescription = "Daily forecast $day,",
                modifier = Modifier
                    .size(60.dp),
                alignment = Alignment.Center
            )
            Text(
                modifier = Modifier.align(CenterVertically),
                text = "${degrees}${0x00B0.toChar()}",
                fontSize = 18.sp,
                color = White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

}