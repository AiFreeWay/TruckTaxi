package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.ProfileSettingsFragment

/**
 * Created by root on 03.06.17.
 */
class FmtProfileSettingsVC(fragment: ProfileSettingsFragment) : BaseViewController<ProfileSettingsFragment>(fragment) {

    fun getAcMainVC(): AcMainVC? {
        return getView()?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }
}