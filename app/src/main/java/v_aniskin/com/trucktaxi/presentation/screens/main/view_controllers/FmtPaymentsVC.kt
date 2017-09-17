package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.content.Intent
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.NetworkErrors
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.domain.executors.interfaces.PaymentsExecutor
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.PaymentsFragment
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.activities.PaymentDetailActivity
import javax.inject.Inject

/**
 * Created by root on 28.05.17.
 */
class FmtPaymentsVC(fragment: PaymentsFragment): BaseViewController<PaymentsFragment>(fragment) {

    @Inject
    lateinit var mPaymentsExecutor: PaymentsExecutor

    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    override fun inject() {
        super.inject()
        getAcMainVC()?.getMainScreenComponent()
                ?.inject(this)
    }

    override fun start() {
        super.start()
        getPayments()
    }

    override fun stop() {
        super.stop()
        mSubscriptionContainer.unsubscribeAll()
    }

    fun getAcMainVC(): AcMainVC? {
        return getView()?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }

    fun showPaymentDetailScreen(paymentId: String) {
        val intent = Intent(mView.context, PaymentDetailActivity::class.java)
        intent.putExtra(PaymentDetailActivity.PAYMENT_ID, paymentId)
        getAcMainVC()?.showNewActivityScreen(intent)
    }

    private fun getPayments() {
        mSubscriptionContainer.addSubscription(mPaymentsExecutor.getPayments()
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({orders -> doOnGetPayments(orders)},
                        {error -> doOnError(error)}))
    }

    private fun doOnGetPayments(payments: ModelContainer<List<Payment>>) {
        if (payments.status.equals(ResponseMonade.SUCCESS))
            mView.loadPayments(payments.mData!!)
        else
            showToast(NetworkErrors.getErrorMessageByType(mView.context, payments.error))
    }

    private fun startProgressBar() {
        getAcMainVC()?.startProgressBar()
    }

    private fun stopProgressBar() {
        getAcMainVC()?.stopProgressBar()
    }

    private fun showToast(message: String) {
        getAcMainVC()?.showToast(message)
    }

    private fun doOnError(error: Throwable) {
        showToast(mView.context.getString(R.string.load_on_error_data))
        stopProgressBar()
        Logger.logError(error)
    }
}