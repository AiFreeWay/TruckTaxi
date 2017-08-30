package v_aniskin.com.trucktaxi.application.di.components

import dagger.Component
import v_aniskin.com.trucktaxi.application.di.modules.MapOrderModule
import v_aniskin.com.trucktaxi.application.di.scopes.PerMapOrder
import v_aniskin.com.trucktaxi.data.location.LocationController
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers.AcMapOrderDetailVC

/**
 * Created by root on 25.08.17.
 */
@PerMapOrder
@Component(modules = arrayOf(MapOrderModule::class), dependencies = arrayOf(ScreenComponent::class))
interface MapOrderComponent {

    fun provideLocationController(): LocationController
    fun provideRepository() : Repository

    fun inject(viewController : AcMapOrderDetailVC)
}