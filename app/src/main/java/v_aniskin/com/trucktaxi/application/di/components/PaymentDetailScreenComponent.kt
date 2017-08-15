package v_aniskin.com.trucktaxi.application.di.components

import dagger.Component
import v_aniskin.com.trucktaxi.application.di.modules.PaymentScreenDetailModule
import v_aniskin.com.trucktaxi.application.di.scopes.PerPaymentDetailScreen
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.view_controllers.AcPaymentDetailVC

/**
 * Created by root on 01.06.17.
 */
@PerPaymentDetailScreen
@Component(modules = arrayOf(PaymentScreenDetailModule::class), dependencies = arrayOf(ScreenComponent::class))
interface PaymentDetailScreenComponent {

    fun inject(viewController : AcPaymentDetailVC)
}