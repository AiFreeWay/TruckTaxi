package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.ProfileExecutor
import v_aniskin.com.trucktaxi.domain.mappers.ProfileMapper
import v_aniskin.com.trucktaxi.domain.mappers.WorkStateMapper
import v_aniskin.com.trucktaxi.domain.models.Profile
import v_aniskin.com.trucktaxi.domain.models.WorkState
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject

/**
 * Created by root on 22.07.17.
 */
@PerMainScreen
class ProfileExecutorImpl @Inject constructor(var mRepository: Repository): ProfileExecutor {

    init {
        Logger.testLog("ProfileExecutorImpl Create")
    }

    override fun getProfile(): Observable<Profile> {
        return mRepository.getProfile()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {response -> ProfileMapper.mapProfile(response)}
    }

    override fun setWorkState(state: Int): Observable<WorkState> {
        return mRepository.setWorkState(state)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { WorkStateMapper.mapWorkState(it) }
    }
}