package com.example.gmap.viewmodel.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNewUpdate: Boolean,
    val badgeCount: Int? = null
)