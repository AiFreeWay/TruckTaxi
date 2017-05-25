package v_aniskin.com.trucktaxi.data.repository

import android.content.Context
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by root on 24.05.17.
 */
@Singleton
class RepositoryImpl : Repository {

    @Inject
    constructor(context : Context) {
        Logger.testLog("RepositoryImpl Create")
    }
}