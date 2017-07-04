package v_aniskin.com.trucktaxi.data.repository

import android.content.Context
import rx.Observable
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.HawkController
import v_aniskin.com.trucktaxi.domain.mappers.UsersMapper
import v_aniskin.com.trucktaxi.data.network_client.NetworkClient
import v_aniskin.com.trucktaxi.data.network_client.requests.AuthRequest
import v_aniskin.com.trucktaxi.data.network_client.responses.AuthResponse
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by root on 24.05.17.
 */
@Singleton
class RepositoryImpl @Inject constructor(context: Context) : Repository {

    private val mNetworkClinet: NetworkClient
    private val mHawkController: HawkController

    init {
        Logger.testLog("RepositoryImpl Create")
        mNetworkClinet = NetworkClient()
        mHawkController = HawkController()
    }

    override fun auth(authRequest: AuthRequest): Observable<AuthResponse> {
        return mNetworkClinet.auth(authRequest)
    }

    override fun getToken(): String {
        return mHawkController.getString(HawkController.TOKEN)
    }

    override fun putToken(token: String) {
        mHawkController.putString(HawkController.TOKEN, token)
    }
}