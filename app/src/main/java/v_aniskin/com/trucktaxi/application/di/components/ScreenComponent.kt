package v_aniskin.com.trucktaxi.application.di.components

import dagger.Component
import v_aniskin.com.trucktaxi.application.di.modules.ScreenModule
import v_aniskin.com.trucktaxi.application.di.scopes.PerScreen

/**
 * Created by root on 25.05.17.
 */
@PerScreen
@Component(modules = arrayOf(ScreenModule::class), dependencies = arrayOf(RootComponent::class))
interface ScreenComponent {

}