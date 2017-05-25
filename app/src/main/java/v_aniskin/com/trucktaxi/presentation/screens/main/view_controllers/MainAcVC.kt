package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment

/**
 * Created by root on 25.05.17.
 */
class MainAcVC(activity: MainActivity) : BaseViewController<MainActivity>(activity) {

    init {
        Logger.testLog("MainAcVC Create")
    }

    override fun start() {
        mView?.getRouter()?.newRootScreen(AuthFragment.AUTH_FRAGMENT_ID, 0)
    }
}