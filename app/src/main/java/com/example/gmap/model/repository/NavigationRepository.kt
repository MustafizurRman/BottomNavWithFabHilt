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
import com.example.gmap.viewmodel.model.BottomNavigationItem

class NavigationRepository {
    fun getNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNewUpdate = false
            ),
            BottomNavigationItem(
                title = "Map",
                selectedIcon = Icons.Filled.LocationOn,
                unselectedIcon = Icons.Outlined.LocationOn,
                hasNewUpdate = true
            ),
            BottomNavigationItem(
                title = "Search",
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search,
                hasNewUpdate = false
            ),
            BottomNavigationItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                hasNewUpdate = false
            ),
            BottomNavigationItem(
                title = "Profile",
                selectedIcon = Icons.Filled.ManageAccounts,
                unselectedIcon = Icons.Outlined.ManageAccounts,
                hasNewUpdate = false
            )
        )
    }
}