package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.content.Intent
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.NetworkErrors
import v_aniskin.com.trucktaxi.application.utils.OrdersTypes
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.OrdersFragment
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.MapOrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity.Companion.ORDER_DETAIL_ACTIVITY_ID
import javax.inject.Inject

/**
 * Created by root on 28.05.17.
 */
class FmtOrdersVC(fragment: OrdersFragment) : BaseViewController<OrdersFragment>(fragment) {

    @Inject
    lateinit var mOrdersExecutor: OrdersExecutor

    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    override fun inject() {
        super.inject()
        getAcMainVC()?.getMainScreenComponent()
                ?.inject(this)
    }

    override fun start() {
        super.start()
        getOrders()
    }

    override fun stop() {
        super.stop()
        mSubscriptionContainer.unsubscribeAll()
    }

    private fun getOrders() {
        val orderObservable = when (mView.getFragmentType()) {
            OrdersFragment.FRAGMENT_TYPE_FUTURE -> mOrdersExecutor.getFeatureOrders()
            OrdersFragment.FRAGMENT_TYPE_CURRENT -> mOrdersExecutor.getCurrentOrders()
            OrdersFragment.FRAGMENT_TYPE_HISTORY -> mOrdersExecutor.getHistoryOrders()
            else -> throw RuntimeException(mView.getString(R.string.orders_fragment_type_error))
        }

        mSubscriptionContainer.addSubscription(orderObservable
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({orders -> doOnGetOrders(orders)},
                        {error -> doOnError(error)}))
    }

    private fun doOnGetOrders(orders: ModelContainer<List<Order>>) {
        if (orders.status.equals(ResponseMonade.SUCCESS))
            mView.loadOrders(orders.mData!!)
        else
            showToast(NetworkErrors.getErrorMessageByType(mView.context, orders.error))
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

    fun getAcMainVC(): AcMainVC? {
        return getView()?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }

    fun showOrderDetailScreen(orderId: String?, status: String?) {
        startProgressBar()
        val intent: Intent
        if (status == OrdersTypes.ORDER_STATUS_CURRENT) {
            intent = Intent(mView.context, MapOrderDetailActivity::class.java)
            intent.putExtra(MapOrderDetailActivity.ORDER_ID_KEY, orderId)
        } else if (status == OrdersTypes.ORDER_STATUS_APPOINTED) {
            intent = Intent(mView.context, OrderDetailActivity::class.java)
            intent.putExtra(ORDER_DETAIL_ACTIVITY_ID, status)
            intent.putExtra(OrderDetailActivity.ORDER_ID_KEY, orderId)
        } else {
            intent = Intent(mView.context, OrderDetailActivity::class.java)
            intent.putExtra(ORDER_DETAIL_ACTIVITY_ID, status)
            intent.putExtra(OrderDetailActivity.ORDER_ID_KEY, orderId)
        }
        getAcMainVC()?.showNewActivityScreen(intent)
    }
}