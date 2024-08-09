package com.example.gmap.view.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gmap.view.ui.components.BottomNavigationBar
import com.example.gmap.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.height(65.dp),
                    cutoutShape = RectangleShape,
                    elevation = 25.dp
                ) {
                    BottomNavigationBar(navController = navController)
                }
            },
            /*                    val items = listOf(
                                    NavigationItem.Home,
                                    NavigationItem.Search
                                )
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentRoute = navBackStackEntry?.destination?.route

                                items.forEach { item ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                imageVector = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        },
                                        selected = currentRoute == item.route,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                                            indicatorColor = Color.Transparent
                                        )
                                    )
                                }
                            }
                        },*/
            floatingActionButton = {
                FloatingActionButton(onClick = { }, shape = CircleShape) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search Button")
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(Modifier.padding(innerPadding))
                }
                composable("search") {
                    MapScreen(Modifier.padding(innerPadding))
                }
            }

            if (!isNetworkAvailable) {
                //Snackbar if there is no network
                LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar(
                        message = "No Network Available",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
}
