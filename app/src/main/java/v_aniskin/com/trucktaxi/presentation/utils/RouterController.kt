package v_aniskin.com.trucktaxi.presentation.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger


/**
 * Created by root on 19.08.17.
 */
class RouterController(private val mMap: GoogleMap, private val mContext: Context) {

    private val NEAR_DISTANCE_IN_METTERS = 15
    private val OPTIMAL_ZOOM = 17F

    private var mCurrentMarker: Marker? = null
    private var mRoutePoints: List<LatLng>? = null
    private var mRotateEnabled = false

    init {
        mMap.clear()
        mMap.animateCamera(CameraUpdateFactory.zoomBy(OPTIMAL_ZOOM))
    }

    fun createRote(points: List<LatLng>) {
        mMap.clear()
        mRoutePoints = points
        val route = PolylineOptions()
        mRoutePoints!!.forEach {
            route.add(it)
        }

        route.color(ContextCompat.getColor(mContext, R.color.doveGray))
        route.width(3f)

        val firstPoints = mRoutePoints!![0]
        val lastPoint = mRoutePoints!![mRoutePoints!!.size-1]

        mMap.addPolyline(route)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(firstPoints, OPTIMAL_ZOOM))
        mMap.addMarker(MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.finish))
                .title(mContext.getString(R.string.destination_point))
                .position(lastPoint)
                .draggable(false))
                .showInfoWindow()
    }

    fun showCurrentPoint(currentPoint: LatLng) {
        if (mCurrentMarker != null)
            mCurrentMarker?.position = currentPoint
        else {
            mCurrentMarker = mMap.addMarker(MarkerOptions()
                    .title(mContext.getString(R.string.i_am))
                    .position(currentPoint)
                    .draggable(false))
            mCurrentMarker!!.showInfoWindow()
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPoint, 17F))
        }
        rotateMap(currentPoint)
    }

    fun showFinishPoint(currentPoint: LatLng) {
        mMap.addMarker(MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.finish))
                .title(mContext.getString(R.string.destination_point))
                .position(currentPoint)
                .draggable(false))
                .showInfoWindow()
    }

    fun showStartPoint(currentPoint: LatLng) {
        mMap.addMarker(MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_start))
                .title(mContext.getString(R.string.start_point))
                .position(currentPoint)
                .draggable(false))
                .showInfoWindow()
    }

    fun setRotateEnabled(enabled: Boolean) {
        mRotateEnabled = enabled
    }

    private fun rotateMap(currentPoint: LatLng) {
        if (!mRotateEnabled || !isRouteEnable())
            return

        val neareastPoint = findNearetsPointFromCurrentPoint(currentPoint)
        if (isPositianANearPositionB(currentPoint, neareastPoint)) {
            val bearing = calculateBearing(currentPoint, neareastPoint)
            bearingMapCamera(bearing)
        } else {
            val nextPointAfterNearest = getNextPointAfterPoint(neareastPoint)
            var bearing = 0F
            if (nextPointAfterNearest != null)
                bearing = calculateBearing(neareastPoint, nextPointAfterNearest)
            else
                bearing = calculateBearing(currentPoint, neareastPoint)
            bearingMapCamera(bearing)
        }
    }

    private fun findNearetsPointFromCurrentPoint(currentPoint: LatLng): LatLng {
        var smallestDistance = -1F
        var nearPointsId = -1

        for (i in mRoutePoints!!.indices) {
            val distance =  getDistanceBetweenPoints(mRoutePoints!![i], currentPoint)
            if (distance<smallestDistance) {
                smallestDistance = distance
                nearPointsId = i
            }
        }
        return mRoutePoints!![nearPointsId]
    }

    private fun getNextPointAfterPoint(currentPoint: LatLng): LatLng? {
        var isCurrent = false
        mRoutePoints!!.forEach {
            if (isCurrent)
                return it
            isCurrent = it.latitude == currentPoint.latitude && it.longitude == currentPoint.longitude
        }
        return null
    }

    private fun isPositianANearPositionB(a: LatLng, b: LatLng): Boolean {
        return getDistanceBetweenPoints(a, b) <= NEAR_DISTANCE_IN_METTERS
    }

    private fun getDistanceBetweenPoints(a: LatLng, b: LatLng): Float {
        val loc1 = Location("")
        loc1.latitude = a.latitude
        loc1.longitude = a.longitude
        val loc2 = Location("")
        loc2.latitude = b.latitude
        loc2.longitude = b.longitude
        return loc1.distanceTo(loc2)
    }

    private fun calculateBearing(a: LatLng, b: LatLng): Float {
        val dLon = b.longitude - a.longitude
        val y = Math.sin(dLon) * Math.cos(b.latitude)
        val x = Math.cos(a.latitude) * Math.sin(b.latitude) - Math.sin(a.latitude) * Math.cos(b.latitude) * Math.cos(dLon)
        var brng = Math.toDegrees(Math.atan2(y, x))
        brng = 360 - (brng + 360) % 360
        return brng.toFloat()
    }

    private fun bearingMapCamera(bearing: Float) {
        val camPos = CameraPosition
                .builder(mMap.cameraPosition)
                .bearing(bearing)
                .build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos))
    }

    private fun isRouteEnable(): Boolean = mRoutePoints != null && mRoutePoints!!.isNotEmpty()
}