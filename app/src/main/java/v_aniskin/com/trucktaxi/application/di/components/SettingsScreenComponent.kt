package v_aniskin.com.trucktaxi.application.di.components

import dagger.Component
import v_aniskin.com.trucktaxi.application.di.modules.SettingsScreenModule
import v_aniskin.com.trucktaxi.application.di.scopes.PerSettingsScreen
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtSettingsVC

/**
 * Created by root on 03.06.17.
 */
@PerSettingsScreen
@Component(modules = arrayOf(SettingsScreenModule::class), dependencies = arrayOf(ScreenComponent::class))
interface SettingsScreenComponent {

    fun inject(viewController : FmtSettingsVC)
}