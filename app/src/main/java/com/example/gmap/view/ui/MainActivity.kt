package com.example.gmap.view.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.PermissionUtil
import com.example.gmap.view.ui.screens.MainScreen
import com.example.gmap.view.ui.theme.GmapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (isGranted) {
            setContent{
                MainScreen()
            }
        } else {
            Log.d("MainActivity", "Permission Not Given: ")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmapTheme {
                if(PermissionUtil.hasLocationPermission(this)) {
                    MainScreen()
                }
                else PermissionUtil.requestLocationPermission(locationPermissionLauncher)
            }
        }
    }
}