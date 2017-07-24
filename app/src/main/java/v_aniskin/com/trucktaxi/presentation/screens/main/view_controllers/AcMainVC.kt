package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import android.content.Intent
import android.widget.Toast
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.di.components.DaggerMainScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.MainScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.MainScreenModule
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.NetworkErrors
import v_aniskin.com.trucktaxi.domain.executors.interfaces.AuthExecutor
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.presentation.navigators.MainFragmentNavigator
import v_aniskin.com.trucktaxi.presentation.navigators.MainFragmentNavigator.Companion.EMPTY_DATA
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.HomeFragment
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
    @Inject
    lateinit var mAuthExecutor: AuthExecutor

    private var mMainScreenComponent: MainScreenComponent? = null
    private var isCiceroneInit = false

    init {
        Logger.testLog("AcMainVC Create")
        initCicerone()
    }

    override fun start() {
        mAuthExecutor.isHaveLocalToken()
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({responseMonade -> doCheckToken(responseMonade)},
                        {error -> doOnError(error)})
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

    fun replaceFragmentScreen(key: String) {
        mRouter.replaceScreen(key, EMPTY_DATA)
    }

    fun showNewActivityScreen(intent: Intent) {
        mView.startActivity(intent)
    }

    fun showToast(text: String) {
        Toast.makeText(mView, text, Toast.LENGTH_SHORT).show()
    }

    fun startProgressBar() {
        mView.startProgress()
    }

    fun stopProgressBar() {
        mView.stopProgress()
    }

    private fun initCicerone() {
        if (mMainScreenComponent != null && !isCiceroneInit) {
            Logger.testLog("AcMainVC init Cicerone")
            mNavigatorHolder.setNavigator(mMainFragmentNavigator)
            isCiceroneInit = true
        }
    }

    private fun doCheckToken(responseMonade: ResponseMonade?) {
        if (responseMonade != null && responseMonade.status.equals(ResponseMonade.SUCCESS)) {
            replaceFragmentScreen(HomeFragment.HOME_FRAGMENT_ID)
        } else if (responseMonade != null) {
            replaceFragmentScreen(AuthFragment.AUTH_FRAGMENT_ID)
            showToast(NetworkErrors.getErrorMessageByType(mView, responseMonade!!.error))
        } else {
            replaceFragmentScreen(AuthFragment.AUTH_FRAGMENT_ID)
        }
    }

    private fun doOnError(error: Throwable) {
        showToast(mView.getString(R.string.load_on_error_data))
        stopProgressBar()
        Logger.logError(error)
    }

    fun getRouter(): Router {
        return mRouter
    }

    fun getMainScreenComponent(): MainScreenComponent? {
        return mMainScreenComponent
    }
}