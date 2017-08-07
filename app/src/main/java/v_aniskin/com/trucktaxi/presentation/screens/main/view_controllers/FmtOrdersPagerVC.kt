package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.os.Bundle
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.OrdersFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.OrdersPagerFragment
import java.util.*

/**
 * Created by root on 07.08.17.
 */
class FmtOrdersPagerVC(fragment: OrdersPagerFragment) : BaseViewController<OrdersPagerFragment>(fragment)  {

    override fun start() {
        super.start()
        val ordersFragments: ArrayList<ViewPagerItemContainer> = ArrayList()
        ordersFragments.add(ViewPagerItemContainer(mView.getString(R.string.orders_current), generateOrderFragment(OrdersFragment.FRAGMENT_TYPE_CURRENT)))
        ordersFragments.add(ViewPagerItemContainer(mView.getString(R.string.orders_future), generateOrderFragment(OrdersFragment.FRAGMENT_TYPE_FUTURE)))
        ordersFragments.add(ViewPagerItemContainer(mView.getString(R.string.orders_history), generateOrderFragment(OrdersFragment.FRAGMENT_TYPE_HISTORY)))
        mView.loadOrdersFragments(ordersFragments)
    }

    fun generateOrderFragment(fragmentType: Int): OrdersFragment {
        val fragment = OrdersFragment()
        val bundle: Bundle = Bundle()
        bundle.putInt(OrdersFragment.FRAGMENT_TYPE_BY_ORDER, fragmentType)
        fragment.arguments = bundle
        return fragment
    }
}