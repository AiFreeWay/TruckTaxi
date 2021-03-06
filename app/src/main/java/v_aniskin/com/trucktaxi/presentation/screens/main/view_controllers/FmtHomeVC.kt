package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.NetworkErrors
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.domain.executors.interfaces.NotificationsExecutor
import v_aniskin.com.trucktaxi.domain.executors.interfaces.ProfileExecutor
import v_aniskin.com.trucktaxi.domain.models.Notification
import v_aniskin.com.trucktaxi.domain.models.Profile
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.HomeFragment
import javax.inject.Inject

/**
 * Created by root on 26.05.17.
 */
class FmtHomeVC(fragment: HomeFragment) : BaseViewController<HomeFragment>(fragment) {

    @Inject
    lateinit var mProfileExecutor: ProfileExecutor
    @Inject
    lateinit var mNotificationsExecutor: NotificationsExecutor

    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    override fun inject() {
        super.inject()
        getAcMainVC()?.getMainScreenComponent()
                ?.inject(this)
    }

    override fun start() {
        super.start()
        getProfile()
        getNotifications()
    }

    override fun stop() {
        super.stop()
        mSubscriptionContainer.unsubscribeAll()
    }

    fun getAcMainVC(): AcMainVC? {
        return getView()?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }

    fun editProfile(workState: String) {
        mSubscriptionContainer.addSubscription(mProfileExecutor.editProfile(workState)
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({}, {error -> doOnError(error)
                    mView.revertSwtOnWork()}))
    }

    private fun getProfile() {
        mSubscriptionContainer.addSubscription(mProfileExecutor.getProfile()
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({profile -> doOnGetProfile(profile)},
                        {error -> doOnError(error)}))
    }

    private fun getNotifications() {
        mSubscriptionContainer.addSubscription(mNotificationsExecutor.getNotifications()
                .doOnSubscribe { startProgressBar() }
                .doOnCompleted { stopProgressBar() }
                .subscribe({profile -> doOnGetNotificationse(profile)},
                        {error -> doOnError(error)}))
    }

    private fun doOnGetProfile(profile: Profile) {
        if (profile.status.equals(ResponseMonade.SUCCESS))
            mView.loadProfile(profile)
        else
            showToast(NetworkErrors.getErrorMessageByType(mView.context, profile.error))
    }

    private fun doOnGetNotificationse(notifications: ModelContainer<List<Notification>>) {
        if (notifications.status.equals(ResponseMonade.SUCCESS))
            mView.loadNotifications(notifications.mData!!)
        else
            showToast(NetworkErrors.getErrorMessageByType(mView.context, notifications.error))
    }

    private fun startProgressBar() {
        getAcMainVC()?.startProgressBar()
        mView.lockSwtOnWork()
    }

    private fun stopProgressBar() {
        getAcMainVC()?.stopProgressBar()
        mView.unlockSwtOnWork()
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