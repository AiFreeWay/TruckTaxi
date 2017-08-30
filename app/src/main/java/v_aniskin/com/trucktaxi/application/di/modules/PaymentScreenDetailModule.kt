package v_aniskin.com.trucktaxi.application.di.modules

import dagger.Module
import dagger.Provides
import v_aniskin.com.trucktaxi.application.di.scopes.PerPaymentDetailScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.factories.PaymentsFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.activities.PaymentDetailActivity

/**
 * Created by root on 01.06.17.
 */
@Module
class PaymentScreenDetailModule(private val mActivity: PaymentDetailActivity) {

    private val mFactory: PaymentsFragmentFactory

    init {
        Logger.testLog("PaymentScreenDetailModule create")
        mFactory = PaymentsFragmentFactory()
    }

    @Provides
    @PerPaymentDetailScreen
    fun providePaymentsFragmentFactory() : PaymentsFragmentFactory {
        return mFactory
    }
}