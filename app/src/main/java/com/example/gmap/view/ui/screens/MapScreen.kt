package com.example.gmap.view.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gmap.viewmodel.MapsViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(viewModel: MapsViewModel = hiltViewModel()) {
    val points: MutableList<LatLng> = mutableListOf()
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false,
            zoomGesturesEnabled = true,
            rotationGesturesEnabled = true,
            myLocationButtonEnabled = true
        )
    }

    val dhakaLatLng = LatLng(23.8103, 90.4125)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(dhakaLatLng, 12f)
    }
    val state by viewModel.state.collectAsState()
    val polylines by viewModel.polylines.collectAsState()


    Scaffold(
        scaffoldState = scaffoldState,
    ) { paddingValues ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            properties = state.properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng -> viewModel.onMapClick(latLng) },
            onMapLongClick = { latLng -> viewModel.onMapLongClick(latLng) }
        ) {
            state.userLocation?.let { userLocation ->
                Marker(
                    state = MarkerState(position = userLocation),
                    title = "You are here",
                )
            }
            state.markers.forEach { marker ->
                Marker(
                    state = MarkerState(position = marker.position),
                    title = "Favorites",
                    snippet = "Saved Locations",
                    onClick = {
                        viewModel.onMarkerClick(marker)
                        true
                    }
                )
            }
            //viewModel.setPolylines(listOf(polylineData))
            // Draw polylines
            polylines.forEach { polylineData ->
                Polyline(
                    points = polylineData.points,
                    color = polylineData.color
                )
            }
        }
    }
}
