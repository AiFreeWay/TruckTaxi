package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.responses.NotificationsResponse
import v_aniskin.com.trucktaxi.domain.models.Notification
import java.util.*

/**
 * Created by root on 22.07.17.
 */
class NotificationsMapper {

    companion object {

        fun mapNotificationsResponse(reponse: NotificationsResponse): List<Notification> {
            return reponse.notifications
        }
    }
}