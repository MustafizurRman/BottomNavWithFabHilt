package com.example.gmap.viewmodel.model

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng

data class PolylineData(
    val points: List<LatLng>,
    val color: Color = Color.Blue
)