package v_aniskin.com.trucktaxi.domain.executors

import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.LocationExecutor
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by root on 31.07.17.
 */
@Singleton
class LocationExecutorImpl @Inject constructor(var mRepository: Repository) : LocationExecutor {

    init {
        Logger.testLog("LocationExecutorImpl Create")
    }

    override fun startLocationUpdate() {
        mRepository.startScanLocation()
    }

    override fun stopLocationUpdate() {
        mRepository.stopScanLocation()
    }
}