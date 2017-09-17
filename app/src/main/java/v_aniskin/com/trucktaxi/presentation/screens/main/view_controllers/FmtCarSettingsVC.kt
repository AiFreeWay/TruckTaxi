package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.NetworkErrors
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.domain.executors.interfaces.ProfileExecutor
import v_aniskin.com.trucktaxi.domain.models.Profile
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.CarSettingsFragment
import java.io.File
import javax.inject.Inject

/**
 * Created by root on 03.06.17.
 */
class FmtCarSettingsVC(fragment: CarSettingsFragment) : BaseViewController<CarSettingsFragment>(fragment) {

    @Inject
    lateinit var mProfileExecutor: ProfileExecutor

    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    override fun inject() {
        super.inject()
        getAcMainVC()?.getMainScreenComponent()
                ?.inject(this)
    }

    override fun start() {
        super.start()
        getProfile()
    }

    override fun stop() {
        super.stop()
        mSubscriptionContainer.unsubscribeAll()
    }

    fun getAcMainVC(): AcMainVC? {
        return getView()?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }

    fun loadImage(imageType: Int, file: File) {
        mSubscriptionContainer.addSubscription(mProfileExecutor.loadImage(imageType, file)
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({ mView.loadImage(imageType, file) }, {error -> doOnError(error)}))
    }

    private fun getProfile() {
        mSubscriptionContainer.addSubscription(mProfileExecutor.getProfile()
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({profile -> doOnGetProfile(profile)},
                        {error -> doOnError(error)}))
    }

    private fun doOnGetProfile(profile: Profile) {
        if (profile.status.equals(ResponseMonade.SUCCESS))
            mView.loadProfile(profile)
        else
            showToast(NetworkErrors.getErrorMessageByType(mView.context, profile.error))
    }

    private fun startProgressBar() {
        getAcMainVC()?.startProgressBar()
    }

    private fun stopProgressBar() {
        getAcMainVC()?.stopProgressBar()
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