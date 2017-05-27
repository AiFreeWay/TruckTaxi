package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.OrdersFragment

/**
 * Created by root on 28.05.17.
 */
class FmtOrdersVC(fragment: OrdersFragment): BaseViewController<OrdersFragment>(fragment) {

    fun getAcMainVC(): AcMainVC? {
        return getView()
                ?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }
}