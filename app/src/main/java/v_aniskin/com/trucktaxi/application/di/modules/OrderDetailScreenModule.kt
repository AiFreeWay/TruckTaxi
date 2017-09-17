package v_aniskin.com.trucktaxi.application.di.modules

import dagger.Module
import dagger.Provides
import v_aniskin.com.trucktaxi.application.di.scopes.PerOrderDetailScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.OrdersExecutorImpl
import v_aniskin.com.trucktaxi.domain.executors.PaymentsExecutorImpl
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.executors.interfaces.PaymentsExecutor
import v_aniskin.com.trucktaxi.presentation.factories.OrdersPaymentsFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity

/**
 * Created by root on 01.06.17.
 */
@Module
class OrderDetailScreenModule(private val mActivity: OrderDetailActivity) {

    private val mFactory: OrdersPaymentsFragmentFactory

    init {
        Logger.testLog("OrderDetailScreenModule create")
        mFactory = OrdersPaymentsFragmentFactory()
    }

    @Provides
    @PerOrderDetailScreen
    fun providePaymentsFragmentFactory() : OrdersPaymentsFragmentFactory {
        return mFactory
    }

    @Provides
    @PerOrderDetailScreen
    fun provideOrdersExecutor(ordersExecutor: OrdersExecutorImpl) : OrdersExecutor {
        return ordersExecutor
    }

    @Provides
    @PerOrderDetailScreen
    fun providePaymentsExecutor(paymentsExecutor: PaymentsExecutorImpl) : PaymentsExecutor {
        return paymentsExecutor
    }
}