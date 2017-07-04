package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.domain.models.Auth

/**
 * Created by root on 25.06.17.
 */
interface AuthExecutor {

    fun auth(id: String, password: String):Observable<Auth>

    fun isHaveLocalToken():Observable<Boolean>

    fun logout()
}