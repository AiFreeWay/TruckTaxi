package v_aniskin.com.trucktaxi.domain.executors

import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.AuthExecutor
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject

/**
 * Created by root on 25.06.17.
 */
@PerMainScreen
class AuthExecutorImpl @Inject constructor(mRepository: Repository) : AuthExecutor {

    init {
        Logger.testLog("AuthExecutorImpl Create")
    }

}