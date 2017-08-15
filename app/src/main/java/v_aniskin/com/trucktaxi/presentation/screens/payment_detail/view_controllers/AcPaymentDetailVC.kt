package v_aniskin.com.trucktaxi.presentation.screens.payment_detail.view_controllers

import v_aniskin.com.trucktaxi.application.di.components.DaggerPaymentDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.PaymentDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.PaymentScreenDetailModule
import v_aniskin.com.trucktaxi.presentation.factories.PaymentsFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.activities.PaymentDetailActivity
import javax.inject.Inject

/**
 * Created by root on 01.06.17.
 */
class AcPaymentDetailVC(view: PaymentDetailActivity) : BaseViewController<PaymentDetailActivity>(view) {

    @Inject
    lateinit var mFactory: PaymentsFragmentFactory

    private var mPaymentDetailScreenComponent: PaymentDetailScreenComponent? = null

    override fun inject() {
        mPaymentDetailScreenComponent = DaggerPaymentDetailScreenComponent.builder()
                .screenComponent(mView.getScreenComponet())
                .paymentScreenDetailModule(PaymentScreenDetailModule(mView))
                .build()
        mPaymentDetailScreenComponent?.inject(this)
    }

    override fun start() {
        super.start()
        mView.loadData(mFactory.getAllItems(mView))
    }
}