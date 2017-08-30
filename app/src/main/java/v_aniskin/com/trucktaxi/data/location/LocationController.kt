package v_aniskin.com.trucktaxi.data.location

import android.content.Context
import com.google.android.gms.location.LocationListener
import android.os.Bundle
import android.os.HandlerThread
import android.os.Looper
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.LocationSettingsRequest
import v_aniskin.com.trucktaxi.application.utils.Logger


/**
 * Created by root on 31.07.17.
 */
class LocationController(private val mContext: Context, private val mLocationInstaller: LocationInstaller) {

    private val mLocationLiveData: LocationLiveData = LocationLiveData(this)
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocThread: HandlerThread? = null

    private val mLocationListener: LocationListener = LocationListener { location -> mLocationLiveData.doOnGetLocation(LatLng(location!!.latitude, location.longitude)) }
    private val mGoogleApiErrorCallback: GoogleApiClient.OnConnectionFailedListener = GoogleApiClient.OnConnectionFailedListener { }

    private val mGoogleApiCallback: GoogleApiClient.ConnectionCallbacks = object: GoogleApiClient.ConnectionCallbacks {

        override fun onConnected(p0: Bundle?) {
            startLocationUpdate()
        }

        override fun onConnectionSuspended(p0: Int) {}
    }

    fun initLocationUpdate() {
        if (mGoogleApiClient == null || !mGoogleApiClient!!.isConnected)
            createGoogleApi()
    }

    fun startLocationUpdate() {
        if (mGoogleApiClient!!.isConnected) {
            //initThread()
            val locationRequest = createLocationRequest()
            checkLocationSettings(locationRequest)
        }
    }

    fun stopLocationUpdate() {
        if (mGoogleApiClient != null && mGoogleApiClient!!.isConnected)
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, mLocationListener)
    }

    fun getLocationLiveData(): LocationLiveData = mLocationLiveData

    private fun createGoogleApi() {
        mGoogleApiClient = GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(mGoogleApiCallback)
                .addOnConnectionFailedListener(mGoogleApiErrorCallback)
                .addApi(LocationServices.API)
                .build()
        mGoogleApiClient!!.connect()
    }

    private fun requestLocation(locationRequest: LocationRequest) {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, mLocationListener, Looper.getMainLooper())
    }

    private fun createLocationRequest(): LocationRequest {
        val locationRequest = LocationRequest()
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        return locationRequest
    }

    private fun initThread() {
        if (mLocThread == null)
            mLocThread = HandlerThread("locationTrackingThread")
        if (!mLocThread!!.isAlive)
            mLocThread!!.start()
    }

    fun checkLocationSettings(locationRequest: LocationRequest) {
        Logger.testLog("checkLocationSettings")

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build())
        result.setResultCallback { result1 ->
            val status = result1.status
            when (status.statusCode) {
                CommonStatusCodes.RESOLUTION_REQUIRED -> mLocationInstaller.sendLocationStatus(status)
                else -> requestLocation(locationRequest)
            }
        }
    }

    interface DoOnGetLocation {

        fun doOnGetLocation(latLnt: LatLng)
    }

    interface LocationInstaller {

        fun sendLocationStatus(status: Status)
    }
}