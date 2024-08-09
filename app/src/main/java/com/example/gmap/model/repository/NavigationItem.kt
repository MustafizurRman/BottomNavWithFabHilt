package com.example.gmap.model.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val title: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector) {
    object Home : NavigationItem("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Search : NavigationItem("search", "Search", Icons.Filled.Search, Icons.Outlined.Search)
}