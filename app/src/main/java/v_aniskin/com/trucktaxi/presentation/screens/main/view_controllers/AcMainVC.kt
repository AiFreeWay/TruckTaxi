package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.content.Intent
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import v_aniskin.com.trucktaxi.application.di.components.DaggerMainScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.MainScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.MainScreenModule
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.navigators.MainFragmentNavigator
import v_aniskin.com.trucktaxi.presentation.navigators.MainFragmentNavigator.Companion.EMPTY_DATA
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment
import javax.inject.Inject

/**
 * Created by root on 25.05.17.
 */
class AcMainVC(activity: MainActivity) : BaseViewController<MainActivity>(activity) {

    @Inject
    lateinit var mRouter: Router
    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder
    @Inject
    lateinit var mMainFragmentNavigator: MainFragmentNavigator

    private var mMainScreenComponent: MainScreenComponent? = null
    private var isCiceroneInit = false
    private var mLastScreenKey: String? = null

    init {
        Logger.testLog("AcMainVC Create")
        initCicerone()
    }

    override fun start() {
        showNewScreenChain(AuthFragment.AUTH_FRAGMENT_ID)
    }

    override fun resume() {
        super.resume()
        initCicerone()
    }

    override fun pause() {
        super.pause()
        mNavigatorHolder.removeNavigator();
        isCiceroneInit = false
    }

    override fun inject() {
        mMainScreenComponent = DaggerMainScreenComponent.builder()
                .screenComponent(mView.getScreenComponet())
                .mainScreenModule(MainScreenModule(mView))
                .build()
        mMainScreenComponent?.inject(this)
    }

    fun getLastScreenKey(): String? {
        return mLastScreenKey
    }

    fun showNewScreenChain(key: String) {
        mRouter.newRootScreen(key, EMPTY_DATA)
        mLastScreenKey = key
    }

    fun showNewActivityScreen(intent: Intent) {
        mView.startActivity(intent)
    }

    private fun initCicerone() {
        if (mMainScreenComponent != null && !isCiceroneInit) {
            Logger.testLog("AcMainVC init Cicerone")
            mNavigatorHolder.setNavigator(mMainFragmentNavigator)
            isCiceroneInit = true
        }
    }

    //Getters and Setters
    fun getRouter(): Router {
        return mRouter
    }
}