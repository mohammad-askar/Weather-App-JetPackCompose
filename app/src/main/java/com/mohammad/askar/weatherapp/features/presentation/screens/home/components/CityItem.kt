package com.mohammad.askar.weatherapp.features.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp

@Composable
fun CityItem(
    modifier: Modifier,
    backgroundColor: Color,
    location: String,
    onLongClick: () -> Unit = {}
) {

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .background(Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = location,
                color = Color.White
            )

        }

    }
    /*Row(
        modifier = Modifier.padding(8.dp),
    ) {
        Text(
            text = location,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            fontSize = 16.sp
        )
    }*/
}