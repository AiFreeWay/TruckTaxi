package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.responses.NotificationsResponse
import v_aniskin.com.trucktaxi.domain.executors.interfaces.NotificationsExecutor
import v_aniskin.com.trucktaxi.domain.mappers.NotificationsMapper
import v_aniskin.com.trucktaxi.domain.models.Notification
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import javax.inject.Inject

/**
 * Created by root on 22.07.17.
 */
class NotificationsExecutorImpl @Inject constructor(var mRepository: Repository) : NotificationsExecutor {

    init {
        Logger.testLog("NotificationsExecutorImpl Create")
    }

    override fun getNotifications(): Observable<ModelContainer<List<Notification>>> {
        return mRepository.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {response -> crateModelContainer(response)}
    }

    private fun crateModelContainer(response: NotificationsResponse): ModelContainer<List<Notification>> {
        val notificationsDomain = NotificationsMapper.mapNotificationsResponse(response)
        return ModelContainer(notificationsDomain, response.error, response.status)
    }
}