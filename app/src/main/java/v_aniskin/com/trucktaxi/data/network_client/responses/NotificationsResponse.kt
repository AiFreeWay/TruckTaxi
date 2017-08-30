package v_aniskin.com.trucktaxi.data.network_client.responses

import v_aniskin.com.trucktaxi.domain.models.Notification
import java.util.*

/**
 * Created by root on 22.07.17.
 */
class NotificationsResponse: BaseResponse() {

    var notifications: List<Notification> = Collections.emptyList()
}