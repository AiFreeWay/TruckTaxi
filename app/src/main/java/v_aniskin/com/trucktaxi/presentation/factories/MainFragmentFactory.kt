package v_aniskin.com.trucktaxi.presentation.factories

import android.support.v4.app.Fragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.MainFragment

/**
 * Created by root on 25.05.17.
 */
class MainFragmentFactory : BaseFactory<Fragment> {

    override fun createInstanse(key: String): Fragment {
        when(key) {
            AuthFragment.AUTH_FRAGMENT_ID -> return AuthFragment()
            MainFragment.MAIN_FRAGMENT_ID -> return MainFragment()
            else -> throw RuntimeException("Unknown fragment key")
        }
    }
}