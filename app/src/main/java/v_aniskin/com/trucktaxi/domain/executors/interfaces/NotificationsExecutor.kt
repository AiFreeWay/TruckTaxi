package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.NotificationPresent

/**
 * Created by root on 22.07.17.
 */
interface NotificationsExecutor {

    fun getNotifications(): Observable<ModelsContainer<NotificationPresent>>
}