package v_aniskin.com.trucktaxi.presentation.factories

import android.support.v4.app.Fragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.*

/**
 * Created by root on 25.05.17.
 */
class MainFragmentFactory: BaseFactory<Fragment> {

    companion object {

        fun getMenuPositionByScreenKey(key: String): Int {
            when(key) {
                HomeFragment.HOME_FRAGMENT_ID -> return 0
                OrdersFragment.ORDERS_FRAGMENT_ID -> return 1
                PaymentsFragment.PAYMENTS_FRAGMENT_ID -> return 2
                ChatFragment.CHAT_FRAGMENT_ID -> return 3
                SettingsFragment.SETTINGS_FRAGMENT_ID -> return 4
                else -> return 0
            }
        }
    }

    override fun createInstanse(key: String): Fragment {
        when(key) {
            AuthFragment.AUTH_FRAGMENT_ID -> return AuthFragment()
            HomeFragment.HOME_FRAGMENT_ID -> return HomeFragment()
            OrdersFragment.ORDERS_FRAGMENT_ID -> return OrdersPagerFragment()
            PaymentsFragment.PAYMENTS_FRAGMENT_ID -> return PaymentsFragment()
            ChatFragment.CHAT_FRAGMENT_ID -> return ChatFragment()
            SettingsFragment.SETTINGS_FRAGMENT_ID -> return SettingsFragment()
            else -> throw RuntimeException("Unknown fragment key")
        }
    }
}