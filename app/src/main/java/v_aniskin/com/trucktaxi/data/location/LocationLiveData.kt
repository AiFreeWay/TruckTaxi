package v_aniskin.com.trucktaxi.data.location

import android.arch.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng

/**
 * Created by root on 31.07.17.
 */

class LocationLiveData (val mLocationController: LocationController) : LiveData<LatLng>(), LocationController.DoOnGetLocation {

    override fun onActive() {
        mLocationController.startLocationUpdate()
    }

    override fun onInactive() {
        mLocationController.stopLocationUpdate()
    }

    override fun doOnGetLocation(latLnt: LatLng) {
        value = latLnt
    }
}
