package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.data.location.LocationLiveData

/**
 * Created by root on 31.07.17.
 */
interface LocationExecutor {

    fun startLocationUpdate(): Observable<LocationLiveData>
}