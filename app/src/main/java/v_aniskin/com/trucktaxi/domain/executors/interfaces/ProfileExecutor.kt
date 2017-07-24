package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.domain.models.Profile

/**
 * Created by root on 22.07.17.
 */
interface ProfileExecutor {

    fun getProfile(): Observable<Profile>
}