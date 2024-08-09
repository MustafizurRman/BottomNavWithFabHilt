package com.example.gmap.view.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gmap.view.ui.screens.MainScreen
import com.example.gmap.view.ui.theme.GmapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmapTheme {
                MainScreen()
            }
        }
    }
}