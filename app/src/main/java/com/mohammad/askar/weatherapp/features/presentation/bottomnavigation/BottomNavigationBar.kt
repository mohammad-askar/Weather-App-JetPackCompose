package com.mohammad.askar.weatherapp.features.presentation.bottomnavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mohammad.askar.weatherapp.features.presentation.screens.NavGraphs
import com.mohammad.askar.weatherapp.ui.theme.brightBlue
import com.mohammad.askar.weatherapp.ui.theme.whiteGray
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.spec.NavHostEngine

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    navHostEngine: NavHostEngine,
    showBottomNav: Boolean = true,
    items: List<BottomNavigationItem> = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Alert,
    ),
) {
    Scaffold(
        backgroundColor = brightBlue,
        bottomBar = {
            if (showBottomNav) {
                BottomNavigation(
                    backgroundColor = brightBlue,
                    contentColor = White,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            selectedContentColor = White,
                            unselectedContentColor = whiteGray,
                            alwaysShowLabel = true,
                            selected = item.destination.route.let {
                                currentDestination?.route?.contains(
                                    it
                                )
                            } == true,
                            onClick = {
                                item.destination.route.let {
                                    navController.navigate(it) {
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }

                }
            }
        }

    ) {
//        content(it)
            Box(modifier = Modifier.padding(it)) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController,
                    engine = navHostEngine
                )
            }

    }
}