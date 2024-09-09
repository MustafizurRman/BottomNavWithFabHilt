package com.example.gmap.model.repository

import com.example.gmap.utils.Resource
import com.google.android.gms.maps.model.LatLng

interface MapsRepository {
    suspend fun getDirections(origin:LatLng,destination:LatLng): Resource<Route>
}