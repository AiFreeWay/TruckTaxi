package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.domain.models.Profile
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.domain.models.WorkState
import java.io.File

/**
 * Created by root on 22.07.17.
 */
interface ProfileExecutor {

    fun getProfile(): Observable<Profile>
    fun editProfile(state: String): Observable<WorkState>
    fun exit()
    fun loadImage(imageType: Int, file: File): Observable<ResponseMonade>
}