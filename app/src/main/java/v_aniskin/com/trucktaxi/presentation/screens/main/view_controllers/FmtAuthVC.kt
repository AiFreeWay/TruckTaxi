package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.HomeFragment.Companion.HOME_FRAGMENT_ID

/**
 * Created by root on 26.05.17.
 */
class FmtAuthVC(fragment: AuthFragment) : BaseViewController<AuthFragment>(fragment) {

    fun getAcMainVC(): AcMainVC? {
        return getView()
                ?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }

    fun auth() {
        getAcMainVC()
                ?.showNewScreenChain(HOME_FRAGMENT_ID)
    }
}