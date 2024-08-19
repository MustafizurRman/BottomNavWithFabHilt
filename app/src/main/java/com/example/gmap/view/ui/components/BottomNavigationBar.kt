package com.example.gmap.view.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gmap.model.repository.NavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Map,
        NavigationItem.Settings,
        NavigationItem.Profile
    )
    BottomNavigation(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = Color.LightGray,
        contentColor = Color.Gray

    ) {
        items.forEachIndexed { index, item ->
            val isSelected = currentRoute == item.route
            BottomNavigationItem(
                modifier = Modifier.padding(
                    start = if (index == 2) 40.dp else 0.dp,
                    end = if (index == 1) 40.dp else 0.dp
                ),
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                })
        }
    }
}


