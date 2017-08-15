package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.domain.models.Profile
import v_aniskin.com.trucktaxi.domain.models.WorkState

/**
 * Created by root on 22.07.17.
 */
interface ProfileExecutor {

    fun getProfile(): Observable<Profile>
    fun setWorkState(state: Int): Observable<WorkState>
}