package v_aniskin.com.trucktaxi.application.di.modules

import dagger.Module
import dagger.Provides
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.factories.SettingsFragmentFactory

/**
 * Created by root on 03.06.17.
 */
@Module
class SettingsScreenModule {

    private val mFactory: SettingsFragmentFactory

    init {
        Logger.testLog("SettingsScreenModule create")
        mFactory = SettingsFragmentFactory()
    }

    @Provides
    fun provideSettingsFragmentFactory() : SettingsFragmentFactory {
        return mFactory
    }
}