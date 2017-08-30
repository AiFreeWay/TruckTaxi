package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.domain.models.Notification
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer

/**
 * Created by root on 22.07.17.
 */
interface NotificationsExecutor {

    fun getNotifications(): Observable<ModelContainer<List<Notification>>>
}