package v_aniskin.com.trucktaxi.presentation.screens.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.application.TruckTaxiApp
import v_aniskin.com.trucktaxi.application.di.components.DaggerScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.ScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.ScreenModule

/**
 * Created by root on 25.05.17.
 */

abstract class BaseActivity<VC> : AppCompatActivity(), Screen {

    protected var mScreenComponent: ScreenComponent? = null
    protected var mViewController: VC? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        inject();
    }

    override fun getScreenComponet(): ScreenComponent? {
        return mScreenComponent
    }

    private fun inject() {
        mScreenComponent = DaggerScreenComponent.builder()
                .rootComponent((application as TruckTaxiApp).getAppComponent())
                .screenModule(ScreenModule())
                .build()
    }

    //Getters and Setters
    public fun getViewController(): VC? {
        return mViewController
    }

    abstract public fun getToolbar(): Toolbar;
}