package v_aniskin.com.trucktaxi.application.di.components

import dagger.Component
import v_aniskin.com.trucktaxi.application.di.modules.OrderDetailScreenModule
import v_aniskin.com.trucktaxi.application.di.scopes.PerOrderDetailScreen
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.factories.OrdersPaymentsFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers.AcOrderDetailVC

/**
 * Created by root on 01.06.17.
 */
@PerOrderDetailScreen
@Component(modules = arrayOf(OrderDetailScreenModule::class), dependencies = arrayOf(ScreenComponent::class))
interface OrderDetailScreenComponent {

    fun provideRepository() : Repository

    fun inject(viewController : AcOrderDetailVC)
}