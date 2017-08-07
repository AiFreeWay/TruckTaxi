package v_aniskin.com.trucktaxi.presentation.factories

import android.content.Context
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.CarSettingsFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.ProfileSettingsFragment
import java.util.*

/**
 * Created by root on 03.06.17.
 */
class SettingsFragmentFactory {

    fun getAllItems(context: Context): List<ViewPagerItemContainer> {
        val list: ArrayList<ViewPagerItemContainer> = ArrayList<ViewPagerItemContainer>()
        list.add(ViewPagerItemContainer(context.getString(R.string.personal_data), ProfileSettingsFragment()))
        list.add(ViewPagerItemContainer(context.getString(R.string.car), CarSettingsFragment()))
        return list
    }
}