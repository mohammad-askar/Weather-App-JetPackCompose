package com.mohammad.askar.weatherapp.features.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohammad.askar.weatherapp.ui.theme.brightBlue
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        SearchBar(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .padding(8.dp),
            onSearch = {
                viewModel.search(it)
            }
        )


        Timber.i("Results: ${state.data}")
        if (state.data != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(85.dp),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = brightBlue,
                elevation = 15.dp

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    state.data.location.let {
                        Text(
                            text = it.name,
                            fontSize = 20.sp,
                            color = White,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        )
                    }

                    Column(

                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("https:${state.data.current?.condition?.icon}")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "${state.data.current?.condition?.text}",
                                modifier = Modifier
                                    .size(60.dp)
                            )

                            Text(
                                text = "${state.data.current?.tempC}${0x00B0.toChar()}",
                                style = MaterialTheme.typography.h6.merge(),
                                color = White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(
                                        start = 18.dp,
                                        end = 18.dp,
                                    ),

                                )
                        }

                    }
                }
            }
        }

    }
}

@Composable
fun SearchBar(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val query = viewModel.query.value

    TextField(
        value = query,
        onValueChange = {
            viewModel.setSearchQuery(it)
        },
        placeholder = {
            Text(
                text = "Enter city...",
                color = White
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, CircleShape)
            .background(Color.Transparent, CircleShape),
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
        ),
        textStyle = TextStyle(color = White),
        maxLines = 1,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = White,
                    contentDescription = "Search for City"
                )
            }
        }
    )
}