package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.content.Intent
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.NetworkErrors
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
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
class FmtOrdersVC(fragment: OrdersFragment): BaseViewController<OrdersFragment>(fragment) {

    @Inject
    lateinit var mOrdersExecutor: OrdersExecutor

    override fun inject() {
        super.inject()
        getAcMainVC()?.getMainScreenComponent()
                ?.inject(this)
    }

    override fun start() {
        super.start()
        getOrders()
    }

    private fun getOrders() {
        mOrdersExecutor.getOrders()
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({orders -> doOnGetOrders(orders)},
                        {error -> doOnError(error)})
    }

    private fun doOnGetOrders(orders: ModelsContainer<OrderPresent>) {
        if (orders.status.equals(ResponseMonade.SUCCESS))
            mView.loadOrders(orders.mData)
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

    fun showOrderDetailScreen(state: Int?) {
        var intent: Intent
        if (state == OrderPresent.STATE_CURRENT)
            intent = Intent(mView.context, MapOrderDetailActivity::class.java)
        else if (state == OrderPresent.STATE_NEW) {
            intent = Intent(mView.context, OrderDetailActivity::class.java)
            intent.putExtra(ORDER_DETAIL_ACTIVITY_ID, state)
        } else {
            intent = Intent(mView.context, OrderDetailActivity::class.java)
            intent.putExtra(ORDER_DETAIL_ACTIVITY_ID, state)
        }
        getAcMainVC()?.showNewActivityScreen(intent);
    }
}