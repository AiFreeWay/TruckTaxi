package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.content.Intent
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.OrdersFragment
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.MapOrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity.Companion.ORDER_DETAIL_ACTIVITY_ID

/**
 * Created by root on 28.05.17.
 */
class FmtOrdersVC(fragment: OrdersFragment): BaseViewController<OrdersFragment>(fragment) {

    fun getAcMainVC(): AcMainVC? {
        return getView()
                ?.getBaseActivity<MainActivity>()
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