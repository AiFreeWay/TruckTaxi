package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.responses.NotificationsResponse
import v_aniskin.com.trucktaxi.domain.models.NotificationDomain
import v_aniskin.com.trucktaxi.presentation.models.NotificationPresent
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import java.util.*

/**
 * Created by root on 22.07.17.
 */
class NotificationsMapper {

    companion object {

        fun mapNotificationsResponse(reponse: NotificationsResponse): List<NotificationDomain> {
            return reponse.notifications
        }

        fun mapNotifications(notifications: List<NotificationDomain>, error: String?, status: String?): ModelsContainer<NotificationPresent> {
            val container: ModelsContainer<NotificationPresent> = ModelsContainer(error, status)
            val notificationsMapped: ArrayList<NotificationPresent> = ArrayList()
            notifications.forEach { notification ->
                notificationsMapped.add(mapNotification(notification))
            }
            return container
        }

        fun mapNotification(notification: NotificationDomain): NotificationPresent {
            return NotificationPresent(notification.text, notification.action)
        }
    }
}