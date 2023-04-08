package com.mohammad.askar.weatherapp.features.presentation.screens.home.components

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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohammad.askar.weatherapp.ui.theme.DetailBackground

@Composable
fun DetailsItem(
    text1: String,
    textValue: String
) {
    Card(
        modifier = Modifier
            .size(width = 110.dp, height = 90.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DetailBackground,
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = text1,
                fontSize = 15.sp,
                color = White
            )
            Text(
                text = textValue,
                fontSize = 16.sp,
                color = White
            )
        }
    }
}
