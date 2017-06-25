package v_aniskin.com.trucktaxi.application.di.components

import android.content.Context
import dagger.Component
import v_aniskin.com.trucktaxi.application.di.modules.ScreenModule
import v_aniskin.com.trucktaxi.application.di.scopes.PerScreen
import v_aniskin.com.trucktaxi.domain.repositories.Repository

/**
 * Created by root on 25.05.17.
 */
@PerScreen
@Component(modules = arrayOf(ScreenModule::class), dependencies = arrayOf(RootComponent::class))
interface ScreenComponent {

    fun provideContext() : Context
    fun provideRepository() : Repository
}