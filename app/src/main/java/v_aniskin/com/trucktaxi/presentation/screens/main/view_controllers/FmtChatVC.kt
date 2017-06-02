package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.ChatFragment

/**
 * Created by root on 02.06.17.
 */
class FmtChatVC(fragment: ChatFragment) : BaseViewController<ChatFragment>(fragment) {

    fun getAcMainVC(): AcMainVC? {
        return getView()
                ?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }
}