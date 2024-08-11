package com.example.gmap.model.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val title: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector) {
    object Home : NavigationItem("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Map : NavigationItem("map", "map", Icons.Filled.LocationOn, Icons.Outlined.LocationOn)
    object Settings : NavigationItem("setting", "setting", Icons.Filled.Settings, Icons.Outlined.Settings)
    object Profile : NavigationItem("profile", "profile", Icons.Filled.ManageAccounts, Icons.Outlined.ManageAccounts)

}