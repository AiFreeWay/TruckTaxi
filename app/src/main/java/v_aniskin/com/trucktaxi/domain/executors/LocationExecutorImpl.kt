package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.location.LocationController
import v_aniskin.com.trucktaxi.data.location.LocationLiveData
import v_aniskin.com.trucktaxi.domain.executors.interfaces.LocationExecutor
import javax.inject.Inject
/**
 * Created by root on 31.07.17.
 */
class LocationExecutorImpl @Inject constructor(var mLocationControlelr: LocationController) : LocationExecutor {

    init {
        Logger.testLog("LocationExecutorImpl Create")
    }

    override fun startLocationUpdate(): Observable<LocationLiveData> {
        mLocationControlelr.initLocationUpdate()
        return Observable.just(mLocationControlelr.getLocationLiveData())
    }
}