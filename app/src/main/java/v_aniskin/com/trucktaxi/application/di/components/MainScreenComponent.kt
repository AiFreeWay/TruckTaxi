package v_aniskin.com.trucktaxi.application.di.components

import dagger.Component
import v_aniskin.com.trucktaxi.application.di.modules.MainScreenModule
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.presentation.factories.MainFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.AcMainVC

/**
 * Created by root on 25.05.17.
 */
@PerMainScreen
@Component(modules = arrayOf(MainScreenModule::class), dependencies = arrayOf(ScreenComponent::class))
interface MainScreenComponent {

    fun inject(viewController : AcMainVC)

    fun provideMainFragmentFactory() : MainFragmentFactory
}