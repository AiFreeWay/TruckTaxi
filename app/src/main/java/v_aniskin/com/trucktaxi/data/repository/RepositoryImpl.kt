package v_aniskin.com.trucktaxi.data.repository

import android.content.Context
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.NetworkClient
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by root on 24.05.17.
 */
@Singleton
class RepositoryImpl @Inject constructor(context: Context) : Repository {

    private var mNetworkClinet: NetworkClient

    init {
        Logger.testLog("RepositoryImpl Create")
        mNetworkClinet = NetworkClient()
    }

}