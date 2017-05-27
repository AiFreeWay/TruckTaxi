package v_aniskin.com.trucktaxi.presentation.factories

import android.support.v4.app.Fragment
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.HomeFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.OrdersFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.PaymentsFragment

/**
 * Created by root on 25.05.17.
 */
class MainFragmentFactory : BaseFactory<Fragment> {

    companion object {

        fun getMenuPositionByScreenKey(key: String): Int {
            when(key) {
                HomeFragment.HOME_FRAGMENT_ID -> return 0
                OrdersFragment.ORDERS_FRAGMENT_ID -> return 1
                PaymentsFragment.PAYMENTS_FRAGMENT_ID -> return 2
                else -> return 0
            }
        }
    }

    override fun createInstanse(key: String): Fragment {
        when(key) {
            AuthFragment.AUTH_FRAGMENT_ID -> return AuthFragment()
            HomeFragment.HOME_FRAGMENT_ID -> return HomeFragment()
            OrdersFragment.ORDERS_FRAGMENT_ID -> return OrdersFragment()
            PaymentsFragment.PAYMENTS_FRAGMENT_ID -> return PaymentsFragment()
            else -> throw RuntimeException("Unknown fragment key")
        }
    }
}