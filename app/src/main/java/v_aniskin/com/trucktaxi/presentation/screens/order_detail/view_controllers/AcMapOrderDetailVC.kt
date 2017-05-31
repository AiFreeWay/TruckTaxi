package v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers

import android.content.Intent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.MapOrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity

/**
 * Created by root on 31.05.17.
 */
class AcMapOrderDetailVC(view: MapOrderDetailActivity) : BaseViewController<MapOrderDetailActivity>(view) {

    fun showOrdersDetailScreen() {
        mView.startActivity(Intent(mView, OrderDetailActivity::class.java))
    }
}