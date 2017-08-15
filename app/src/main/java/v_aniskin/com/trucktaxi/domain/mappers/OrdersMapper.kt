package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.application.utils.DateMapper
import v_aniskin.com.trucktaxi.data.network_client.models.OrderNetwork
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import java.util.*

/**
 * Created by root on 25.07.17.
 */
class OrdersMapper {

    companion object {

        fun mapOrders(responseContainer: ModelsContainer<Order>): ModelsContainer<OrderPresent> {
            val mappedContainer: ModelsContainer<OrderPresent> = ModelsContainer(responseContainer.error, responseContainer.status)
            responseContainer.mData.forEach { order ->
                mappedContainer.mData.add(mapOrder(order))
            }
            return mappedContainer
        }

        fun mapOrdersNetwork(orders: List<OrderNetwork>): List<Order> {
            val mappedOrders = ArrayList<Order>()
            orders.forEach { order ->
                mappedOrders.add(mapOrder(order))
            }
            return mappedOrders
        }

        fun mapOrder(order: Order): OrderPresent {
            return OrderPresent(order.desc, order.order, order.beginDate, order.address, order.workTime, order.state, order.status)
        }

        fun mapOrder(order: OrderNetwork): Order {
            val beginTime = DateMapper.mapDateTime(order.orderTimeStart!!)
            val workTime = DateMapper.mapTime(order.orderTimeFinish!!-order.orderTimeStart!!)
            return Order(order.orderId, beginTime, order.address, workTime, order.orderStatus, order.orderPrice)
        }
    }
}