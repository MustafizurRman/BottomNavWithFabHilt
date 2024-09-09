package com.example.gmap.model.repository

import com.example.gmap.model.data.remote.MapDirectionsService
import com.example.gmap.utils.Resource
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(private val directionsService: MapDirectionsService) :
    MapsRepository {
    override suspend fun getDirections(origin: LatLng, destination: LatLng): Resource<Route> {
        return try {
            val response = directionsService.getDirections(
                originLatLng = "${origin.latitude},${origin.longitude}",
                destinationLatLng = "${destination.latitude},${destination.longitude}",
            )

            if (response.isSuccessful && response.body() != null) {

                val polyLinePoints = try {
                    response.body()!!.routes[0].legs[0].steps.map { step ->
                        step.polyline.decodePolyline(step.polyline.points)
                    }
                } catch (e: Exception) {
                    emptyList()
                }
                Resource.Success(data = Route(routePoints = polyLinePoints))
            } else {
                Resource.Error(response.message())
            }

        } catch (e: Exception) {
            Resource.Error("Something went wrong")
        }
    }
}