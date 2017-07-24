package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.NotificationExecutor
import v_aniskin.com.trucktaxi.domain.mappers.NotificationsMapper
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.models.NotificationPresent
import v_aniskin.com.trucktaxi.presentation.models.NotificationsContainer
import javax.inject.Inject

/**
 * Created by root on 22.07.17.
 */
@PerMainScreen
class NotificationExecutorImpl @Inject constructor(var mRepository: Repository) : NotificationExecutor {

    init {
        Logger.testLog("AuthExecutorImpl Create")
    }

    override fun getNotifications(): Observable<NotificationsContainer> {
        return mRepository.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {response ->
                    val notificationsDomain = NotificationsMapper.mapNotificationsResponse(response)
                    NotificationsMapper.mapNotifications(notificationsDomain, response.error, response.status)}
    }
}