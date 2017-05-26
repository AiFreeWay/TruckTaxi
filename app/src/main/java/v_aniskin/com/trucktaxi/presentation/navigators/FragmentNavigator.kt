package v_aniskin.com.trucktaxi.presentation.navigators

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast

import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.factories.MainFragmentFactory

/**
 * Created by root on 25.05.17.
 */
class FragmentNavigator(private val mActivity: Activity, private val mFactory: MainFragmentFactory, fragmentManager: FragmentManager, containerId: Int) : SupportFragmentNavigator(fragmentManager, containerId) {

    companion object {
        const val EMPTY_DATA: Int = 0
    }

    init {
        Logger.testLog("FragmentNavigator Create")
    }

    override fun createFragment(screenKey: String, data: Any): Fragment {
        return mFactory.createInstanse(screenKey)
    }

    override fun showSystemMessage(message: String) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun exit() {
        Logger.testLog("FragmentNavigator Exit")
        mActivity.finish()
    }
}
