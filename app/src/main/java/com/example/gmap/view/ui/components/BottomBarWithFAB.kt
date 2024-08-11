package com.example.gmap.view.ui.components

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gmap.model.repository.MainScreenNavigation

@Composable
fun BottomBarWithFAB() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RectangleShape),
                cutoutShape = CircleShape,
                elevation = 22.dp,
                contentPadding = PaddingValues(0.dp,0.dp),
                backgroundColor = Color.Gray
            ) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = Color.Cyan,
                onClick = { Log.d("bugfind", "Floating Button Clicked") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }
    ) {
        Log.d("bugfind", it.toString())
        MainScreenNavigation(navController)
    }
}