package v_aniskin.com.trucktaxi.data.network_client.responses

import v_aniskin.com.trucktaxi.data.network_client.models.OrderNetwork
import v_aniskin.com.trucktaxi.domain.models.Order
import java.util.*

/**
 * Created by root on 25.07.17.
 */
class OrdersResponse: BaseResponse() {

    var orders: List<OrderNetwork> = Collections.emptyList()
}