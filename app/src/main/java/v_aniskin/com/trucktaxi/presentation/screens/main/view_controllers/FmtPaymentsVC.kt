package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.content.Intent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.PaymentsFragment
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.activities.PaymentDetailActivity

/**
 * Created by root on 28.05.17.
 */
class FmtPaymentsVC(fragment: PaymentsFragment): BaseViewController<PaymentsFragment>(fragment) {

    fun getAcMainVC(): AcMainVC? {
        return getView()
                ?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }

    fun showPaymentDetailScreen() {
        getAcMainVC()?.showNewActivityScreen(Intent(mView.context, PaymentDetailActivity::class.java));
    }
}