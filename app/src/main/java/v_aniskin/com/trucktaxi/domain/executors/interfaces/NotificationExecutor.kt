package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.presentation.models.NotificationsContainer

/**
 * Created by root on 22.07.17.
 */
interface NotificationExecutor {

    fun getNotifications(): Observable<NotificationsContainer>
}