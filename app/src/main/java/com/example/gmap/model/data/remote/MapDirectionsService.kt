package com.example.gmap.model.data.remote

import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.Response

interface MapDirectionsService {
    @GET("directions/json")
    suspend fun getDirections(
        @Query("origin") originLatLng:String,
        @Query("destination") destinationLatLng:String,
        @Query("key") apiKey:String = MAPS_API_KEY
    ):Response<DirectionDto>

    companion object{
        const val MAPS_API_KEY = "AIzaSyATIGIAkzQupH9e7Yi81pUbfateXGWpppo"
    }
}