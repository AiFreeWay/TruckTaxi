package v_aniskin.com.trucktaxi.presentation.screens.payment_detail.view_controllers

import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.di.components.DaggerPaymentDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.PaymentDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.PaymentScreenDetailModule
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.domain.executors.interfaces.PaymentsExecutor
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.activities.PaymentDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.fragments.PaymentFragment
import javax.inject.Inject

/**
 * Created by root on 16.09.17.
 */
class FmtPaymentVC(view: PaymentFragment) : BaseViewController<PaymentFragment>(view) {

    @Inject
    lateinit var mPaymentsExecutor: PaymentsExecutor

    private var mPaymentDetailScreenComponent: PaymentDetailScreenComponent? = null
    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    override fun inject() {
        mPaymentDetailScreenComponent = DaggerPaymentDetailScreenComponent.builder()
                .screenComponent(mView.getScreenComponet())
                .paymentScreenDetailModule(PaymentScreenDetailModule(mView.activity as PaymentDetailActivity))
                .build()
        mPaymentDetailScreenComponent?.inject(this)
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