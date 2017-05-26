package v_aniskin.com.trucktaxi.presentation.screens.common

import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import v_aniskin.com.trucktaxi.application.di.components.ScreenComponent
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity

/**
 * Created by root on 25.05.17.
 */
abstract class BaseParentFragment<VC> : Fragment(), Screen {

    protected var mViewController: VC? = null

    override fun getScreenComponet(): ScreenComponent? {
        return getBaseActivity<BaseActivity<*>>().getScreenComponet()
    }

    fun <AC: BaseActivity<*>> getBaseActivity(): AC {
        return activity as AC
    }

    //Getters and Setters
    public fun getViewController(): VC? {
        return mViewController
    }

    fun getToolbar(): Toolbar {
        return getBaseActivity<BaseActivity<*>>().getToolbar()
    }
}