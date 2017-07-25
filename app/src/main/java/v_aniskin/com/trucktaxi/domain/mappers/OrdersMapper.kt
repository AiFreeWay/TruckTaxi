package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.responses.OrdersResponse
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import java.util.*

/**
 * Created by root on 25.07.17.
 */
class OrdersMapper {

    companion object {

        fun mapOrderResponse(reponse: OrdersResponse): List<Order> {
            return reponse.orders
        }

        fun mapOrders(orders: List<Order>, error: String?, status: String?): ModelsContainer<OrderPresent> {
            val container: ModelsContainer<OrderPresent> = ModelsContainer(error, status)
            val ordersMapped: ArrayList<OrderPresent> = ArrayList()
            orders.forEach { order ->
                ordersMapped.add(mapOrder(order))
            }
            return container
        }

        fun mapOrder(order: Order): OrderPresent {
            return OrderPresent(order.order, order.time, order.address, order.workTime, order.state)
        }
    }
}