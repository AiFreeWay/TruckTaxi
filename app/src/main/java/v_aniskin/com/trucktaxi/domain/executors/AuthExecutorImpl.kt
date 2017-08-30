package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.responses.AuthResponse
import v_aniskin.com.trucktaxi.data.network_client.responses.BaseResponse
import v_aniskin.com.trucktaxi.domain.executors.interfaces.AuthExecutor
import v_aniskin.com.trucktaxi.domain.mappers.UsersMapper
import v_aniskin.com.trucktaxi.domain.models.Auth
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject

/**
 * Created by root on 25.06.17.
 */
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

    override fun isHaveLocalToken(): Observable<ResponseMonade> {
        val token = mRepository.getToken()
        if (!token.isEmpty())
            return updateToken()
                    .map { request ->
                        if (request.status.equals(ResponseMonade.ERROR))
                            mRepository.removeToken()
                        ResponseMonade(request.error, request.status) }
        return Observable.just(null)
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

    private fun updateToken() : Observable<BaseResponse> {
        return mRepository.updateToken()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}