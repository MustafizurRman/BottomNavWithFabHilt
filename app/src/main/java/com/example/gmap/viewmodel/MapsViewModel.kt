package com.example.gmap.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gmap.model.repository.MapState
import com.example.gmap.model.repository.MapsRepository
import com.example.gmap.utils.Resource
import com.example.gmap.viewmodel.model.PolylineData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapsViewModel @Inject constructor(private val mapsRepository: MapsRepository):ViewModel() {
    private val _state = MutableStateFlow(MapState())
    val state: StateFlow<MapState> = _state.asStateFlow()

    private val _polylines = MutableStateFlow<List<PolylineData>>(emptyList())
    val polylines: StateFlow<List<PolylineData>> = _polylines.asStateFlow()

/*//    private val fusedLocationClient: FusedLocationProviderClient =
//        LocationServices.getFusedLocationProviderClient(this)

    init {
        fetchUserLocation()
    }

    private fun fetchUserLocation() {
        viewModelScope.launch {
            if (ActivityCompat.checkSelfPermission(
                    getApplication(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    getApplication(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Request permissions from the user
                return@launch
            }

            val locationResult: Task<Location> = fusedLocationClient.lastLocation
            locationResult.addOnSuccessListener { location ->
                location?.let {
                    val userLatLng = LatLng(it.latitude, it.longitude)
                    updateUserLocation(userLatLng)
                    Log.d("MapsViewModel", "fetchUserLocation: $userLatLng")
                }
            }.addOnFailureListener {
                Log.d("MapsViewModel", "fetchUserLocation: Failed to get location")
            }
        }
    }

    fun updateUserLocation(latLng: LatLng) {
        _state.update { it.copy(userLocation = latLng) }
        addPointToPolyLine(latLng)
    }*/

    fun fetchRoute(origin: LatLng, destination: LatLng){
        viewModelScope.launch{
            val result = mapsRepository.getDirections(origin,destination)
            when(result){
                is Resource.Success ->{
                    _state.update { it.copy() }
                }
                is Resource.Error ->{
                    Log.d("MapsViewModel","Error fetching directions")
                }
            }
        }
    }
    fun onMapClick(latLng: LatLng) {
        //click implementation
    }

    fun onMapLongClick(latLng: LatLng) {
        //long click implementation
        val existingMarker = _state.value.markers.find { it.position == latLng }
        if (existingMarker != null) {
            _state.update { it.copy(markers = it.markers - existingMarker) }
            recalculatePolylines(existingMarker.position)
        } else {
            val newMarker = MarkerState(position = latLng)
            _state.update { it.copy(markers = it.markers + newMarker) }
            addPointToPolyLine(latLng)
        }
    }

    fun onMarkerClick(marker: MarkerState) {
        //market click implementation
    }

    private fun addPointToPolyLine(point:LatLng){
        val currentPoint = _polylines.value.flatMap { it.points }
        val newPoints = currentPoint+ point
        val polylineData = PolylineData(points = newPoints, color = Color.Red)
        _polylines.update { listOf(polylineData) }
    }

    private fun recalculatePolylines(removePoints: LatLng) {
        val currentPoint = _polylines.value.flatMap { it.points }
        val newPoints = currentPoint - removePoints
        val polylineData=  PolylineData(points = newPoints, color = Color.Red)
        _polylines.update { listOf(polylineData) }
    }

    fun setPolylines(newPolylines: List<PolylineData>) {
        _polylines.value = newPolylines
    }
}