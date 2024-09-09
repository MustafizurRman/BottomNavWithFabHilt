package com.example.gmap.model.data.remote

import com.google.android.gms.maps.model.LatLng

data class DirectionDto(
    val geocodedWaypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
) {
    data class GeocodedWaypoint(
        val geocoderStatus: String,
        val placeId: String,
        val type: List<String>
    )

    data class Route(
        val bounds: Bounds,
        val copyrights: String,
        val legs: List<Leg>,
        val overviewPolyline: OverviewPolyline,
        val summary: String,
        val warnings: List<Any>,
        val waypointOrder: List<Any>
    ) {
        data class Bounds(
            val northeast: Northeast,
            val southwest: Southwest
        ) {
            data class Northeast(
                val lat: Double, val lang: Double
            )

            data class Southwest(
                val lat: Double, val lang: Double
            )
        }

        data class Leg(
            val distance: Distance,
            val duration: Duration,
            val endAddress: String,
            val endLocation: EndLocation,
            val startAddress: String,
            val startLocation: StartLocation,
            val steps: List<Step>,
            val trafficSpeedEntry: List<Any>,
            val viaWaypoint: List<Any>
        ) {
            data class Distance(val text: String, val value: Int)
            data class Duration(val text: String, val value: Int)
            data class StartLocation(val lat: Double, val lang: Double)
            data class EndLocation(val lat: Double, val lang: Double)
            data class Step(
                val distance: Distance,
                val duration: Duration,
                val endLocation: EndLocation,
                val htmlInstruction: String,
                val maneuver: String,
                val polyline: Polyline,
                val startLocation: StartLocation,
                val travelMode: String
            ) {
                data class Polyline(val points: String) {
                    fun decodePolyline(encoded: String): List<LatLng> {
                        val poly = ArrayList<LatLng>()
                        var index = 0
                        val len = encoded.length
                        var lat = 0
                        var lng = 0
                        while (index < len) {
                            var b: Int
                            var shift = 0
                            var result = 0
                            do {
                                b = encoded[index++].code - 63
                                result = result or (b and 0x1f shl shift)
                                shift += 5
                            } while (b >= 0x20)
                            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                            lat += dlat
                            shift = 0
                            result = 0
                            do {
                                b = encoded[index++].code - 63
                                result = result or (b and 0x1f shl shift)
                                shift += 5
                            } while (b >= 0x20)
                            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                            lng += dlng
                            val latLng = LatLng((lat.toDouble() / 1E5), (lng.toDouble() / 1E5))
                            poly.add(latLng)
                        }
                        return poly
                    }
                }
            }
        }
        data class OverviewPolyline(val points: String)
    }
}
