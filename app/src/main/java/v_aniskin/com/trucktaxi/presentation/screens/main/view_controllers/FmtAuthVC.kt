package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.NetworkErrors
import v_aniskin.com.trucktaxi.domain.executors.interfaces.AuthExecutor
import v_aniskin.com.trucktaxi.domain.models.Auth
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.AuthFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.HomeFragment.Companion.HOME_FRAGMENT_ID
import javax.inject.Inject

/**
 * Created by root on 26.05.17.
 */
class FmtAuthVC(fragment: AuthFragment) : BaseViewController<AuthFragment>(fragment) {

    @Inject
    lateinit var mAuthExecutor: AuthExecutor

    override fun inject() {
        super.inject()
        getAcMainVC()?.getMainScreenComponent()
                ?.inject(this)
    }

    fun getAcMainVC(): AcMainVC? {
        return getView()?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }

    fun auth(id: String, password: String) {
        if (id == "" || password == "") {
            showToast(getView()!!.getString(R.string.please_fill_fields))
            return
        }
        mAuthExecutor.auth(id, password)
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({auth -> doOnGetAuthResult(auth)},
                        {error -> doOnError(error)})
    }

    private fun doOnGetAuthResult(auth: Auth) {
        if (auth.status.equals(ResponseMonade.SUCCESS))
            openMainScreen()
        else
            showToast(NetworkErrors.getErrorMessageByType(mView.context, auth.error))
    }

    private fun startProgressBar() {
        getAcMainVC()?.startProgressBar()
    }

    private fun stopProgressBar() {
        getAcMainVC()?.stopProgressBar()
    }

    private fun openMainScreen() {
        getAcMainVC()?.replaceFragmentScreen(HOME_FRAGMENT_ID)
    }

    private fun showToast(message: String) {
        getAcMainVC()?.showToast(message)
    }

    private fun doOnError(error: Throwable) {
        showToast(mView.context.getString(R.string.load_on_error_data))
        stopProgressBar()
        Logger.logError(error)
    }
}