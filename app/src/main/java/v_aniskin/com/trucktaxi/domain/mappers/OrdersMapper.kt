package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent

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

        fun mapOrder(order: Order): OrderPresent {
            return OrderPresent(order.desc, order.order, order.time, order.address, order.workTime, order.state, order.status)
        }
    }
}