package v_aniskin.com.trucktaxi.data.location

import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import com.google.android.gms.maps.model.LatLng

/**
 * Created by root on 31.07.17.
 */
class LocationController(val mLocationManager: LocationManager) {

    private val mLocationLiveData: LocationLiveData

    init {
        mLocationLiveData = LocationLiveData(this);
    }

    val mLocationListener: LocationListener = object: LocationListener {

        override fun onLocationChanged(location: Location?) {
            mLocationLiveData.doOnGetLocation(LatLng(location!!.latitude, location.longitude))
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        }

        override fun onProviderEnabled(p0: String?) {
        }

        override fun onProviderDisabled(p0: String?) {
        }

    }

    fun startLocationUpdate() {
        val criteria: Criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE
        mLocationManager.requestLocationUpdates(3000, 5.toFloat(), criteria, mLocationListener, Looper.getMainLooper())
    }

    fun stopLocationUpdate() {
        mLocationManager.removeUpdates(mLocationListener)
    }

    interface DoOnGetLocation {

        fun doOnGetLocation(latLnt: LatLng)
    }
}