package v_aniskin.com.trucktaxi.application.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.repository.RepositoryImpl
import v_aniskin.com.trucktaxi.domain.repositories.Repository

/**
 * Created by root on 25.05.17.
 */
@Module
class RootModule(private val mContext: Context) {

    init {
        Logger.testLog("RootModule create")
    }

    @Provides
    fun  provideContext() : Context {
        return mContext
    }

    @Provides
    fun provideRepository(repository : RepositoryImpl) : Repository {
        return repository
    }
}