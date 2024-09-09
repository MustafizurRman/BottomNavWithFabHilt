package com.example.gmap.model.repository

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerState

data class MapState(
    val properties: MapProperties = MapProperties(),
    val markers: List<MarkerState> = emptyList(),
    val userLocation: LatLng? = null,
)
