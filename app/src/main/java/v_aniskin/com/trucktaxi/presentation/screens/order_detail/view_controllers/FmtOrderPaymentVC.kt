package v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers

import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.di.components.DaggerOrderDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.OrderDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.OrderDetailScreenModule
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.domain.executors.interfaces.PaymentsExecutor
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.fragmens.OrderPaymentFragment
import javax.inject.Inject

/**
 * Created by root on 17.09.17.
 */
class FmtOrderPaymentVC(view: OrderPaymentFragment) : BaseViewController<OrderPaymentFragment>(view) {

    @Inject
    lateinit var mPaymentsExecutor: PaymentsExecutor

    private var mOrderDetailScreenComponent: OrderDetailScreenComponent? = null
    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    override fun inject() {
        mOrderDetailScreenComponent = DaggerOrderDetailScreenComponent.builder()
                .screenComponent(mView.getScreenComponet())
                .orderDetailScreenModule(OrderDetailScreenModule(mView.activity as OrderDetailActivity))
                .build()
        mOrderDetailScreenComponent?.inject(this)
    }

    override fun start() {
        super.start()
        getPayment()
    }

    fun getPayment() {
        mSubscriptionContainer.addSubscription(mPaymentsExecutor
                .getPaymentDetail(mView.getPaymentId(), mView.getPaymentType())
                .doOnSubscribe { mView.startProgress() }
                .subscribe({ doOnGetPayment(it) }, { doOnError(it) }))
    }

    fun doOnGetPayment(payment: ModelContainer<Payment>) {
        mView.stopProgress()
        mView.loadPayment(payment.mData!!)
    }

    private fun doOnError(error: Throwable) {
        mView.stopProgress()
        mView.showToast(R.string.load_on_error_data)
        Logger.logError(error)
    }
}