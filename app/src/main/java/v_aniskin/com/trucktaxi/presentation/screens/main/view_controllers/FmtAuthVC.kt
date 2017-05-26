package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.presentation.navigators.FragmentNavigator.Companion.EMPTY_DATA
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.MainFragment.Companion.MAIN_FRAGMENT_ID

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
                ?.getRouter()
                ?.newRootScreen(MAIN_FRAGMENT_ID, EMPTY_DATA)
    }
}