package v_aniskin.com.trucktaxi.presentation.factories

import v_aniskin.com.trucktaxi.presentation.screens.common.BaseFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment

/**
 * Created by root on 25.05.17.
 */
class MainFragmentFactory : BaseFactory<BaseFragment> {

    override fun createInstanse(key: String): BaseFragment {
        when(key) {
            AuthFragment.AUTH_FRAGMENT_ID -> return AuthFragment()
            else -> throw RuntimeException("Unknown fragment key")
        }
    }
}