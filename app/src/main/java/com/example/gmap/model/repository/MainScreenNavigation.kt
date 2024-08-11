package com.example.gmap.model.repository

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gmap.view.ui.screens.HomeScreen
import com.example.gmap.view.ui.screens.MapScreen

@Composable
fun MainScreenNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("map") {
            MapScreen()
        }
        composable("setting") {
            HomeScreen()
        }
        composable("profile") {
            MapScreen()
        }

    }
}