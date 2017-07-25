package v_aniskin.com.trucktaxi.data.network_client.responses

import v_aniskin.com.trucktaxi.domain.models.NotificationDomain
import java.util.*

/**
 * Created by root on 22.07.17.
 */
class NotificationsResponse: BaseResponse() {

    var notifications: List<NotificationDomain> = Collections.emptyList()
}