package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.NotificationsExecutor
import v_aniskin.com.trucktaxi.domain.mappers.NotificationsMapper
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.NotificationPresent
import javax.inject.Inject

/**
 * Created by root on 22.07.17.
 */
@PerMainScreen
class NotificationsExecutorImpl @Inject constructor(var mRepository: Repository) : NotificationsExecutor {

    init {
        Logger.testLog("NotificationsExecutorImpl Create")
    }

    override fun getNotifications(): Observable<ModelsContainer<NotificationPresent>> {
        return mRepository.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {response ->
                    val notificationsDomain = NotificationsMapper.mapNotificationsResponse(response)
                    NotificationsMapper.mapNotifications(notificationsDomain, response.error, response.status)}
    }
}