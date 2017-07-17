package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.responses.AuthResponse
import v_aniskin.com.trucktaxi.domain.executors.interfaces.AuthExecutor
import v_aniskin.com.trucktaxi.domain.mappers.UsersMapper
import v_aniskin.com.trucktaxi.domain.models.Auth
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject

/**
 * Created by root on 25.06.17.
 */
@PerMainScreen
class AuthExecutorImpl @Inject constructor(var mRepository: Repository) : AuthExecutor {

    init {
        Logger.testLog("AuthExecutorImpl Create")
    }

    override fun auth(id: String, password: String): Observable<Auth> {
        return mRepository.auth(UsersMapper.mapAuth(id, password))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map({response -> onAuth(response)})
    }

    override fun isHaveLocalToken(): Observable<Boolean> {
        val token = mRepository.getToken()
        return Observable.just(!token.isEmpty())
    }

    override fun logout() {
        mRepository.putToken("")
    }

    private fun onAuth(authResponse: AuthResponse) : Auth {
        val auth = UsersMapper.mapAuth(authResponse)
        if (auth.status.equals(ResponseMonade.SUCCESS))
            mRepository.putToken(auth.token!!)
        return auth
    }
}