package v_aniskin.com.trucktaxi.application

import android.support.multidex.MultiDexApplication
import com.orhanobut.hawk.Hawk
import v_aniskin.com.trucktaxi.application.di.components.RootComponent
import v_aniskin.com.trucktaxi.application.di.components.DaggerRootComponent
import v_aniskin.com.trucktaxi.application.di.modules.RootModule


/**
 * Created by root on 24.05.17.
 */

class TruckTaxiApp : MultiDexApplication() {

    private var mRootComponent: RootComponent? = null

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build();
        mRootComponent = DaggerRootComponent.builder()
                .rootModule(RootModule(this))
                .build()
    }

    public fun getAppComponent(): RootComponent? {
        return mRootComponent
    }
}