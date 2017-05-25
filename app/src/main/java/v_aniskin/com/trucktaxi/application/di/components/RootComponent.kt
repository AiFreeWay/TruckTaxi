package v_aniskin.com.trucktaxi.application.di.components

import android.content.Context
import dagger.Component
import v_aniskin.com.trucktaxi.application.TruckTaxiApp
import v_aniskin.com.trucktaxi.application.di.modules.RootModule
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import javax.inject.Singleton

/**
 * Created by root on 25.05.17.
 */
@Singleton
@Component(modules = arrayOf(RootModule::class))
interface RootComponent {

    fun inject(application: TruckTaxiApp)

    fun provideContext() : Context
    fun provideRepository() : Repository
}