package v_aniskin.com.trucktaxi.data.network_client.models

import v_aniskin.com.trucktaxi.domain.models.OrderRoutePointNetwork
import java.util.*

/**
 * Created by root on 08.08.17.
 */
class OrderNetwork() {

    var orderId: String? = null

    var orderTimeStart: Long? = null
    var orderTimeFinish: Long? = null
    var orderWorkTime: String? = null

    var startRoutePointAddress: String? = null
    var finalRoutePointAddress: String? = null

    var orderStatus: String? = null
    var orderPrice: String? = null

    var orderRoutepoints: List<OrderRoutePointNetwork> = Collections.emptyList()
}