package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import v_aniskin.com.trucktaxi.application.di.components.DaggerMainScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.MainScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.MainScreenModule
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.navigators.FragmentNavigator
import v_aniskin.com.trucktaxi.presentation.navigators.FragmentNavigator.Companion.EMPTY_DATA
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
    lateinit var mFragmentNavigator: FragmentNavigator

    private var mMainScreenComponent: MainScreenComponent? = null
    private var isCiceroneInit = false

    init {
        Logger.testLog("AcMainVC Create")
        initCicerone()
    }

    override fun start() {
        mRouter.newRootScreen(AuthFragment.AUTH_FRAGMENT_ID, EMPTY_DATA)
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

    private fun initCicerone() {
        if (mMainScreenComponent != null && !isCiceroneInit) {
            Logger.testLog("AcMainVC init Cicerone")
            mNavigatorHolder.setNavigator(mFragmentNavigator)
            isCiceroneInit = true
        }
    }

    //Getters and Setters
    fun getRouter(): Router {
        return mRouter
    }
}