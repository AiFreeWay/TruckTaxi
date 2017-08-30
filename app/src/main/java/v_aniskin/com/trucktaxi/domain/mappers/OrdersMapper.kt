package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.application.utils.DateMapper
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.models.OrderNetwork
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.domain.models.OrderRoutePoint
import v_aniskin.com.trucktaxi.domain.models.OrderRoutePointNetwork
import java.util.*

/**
 * Created by root on 25.07.17.
 */
class OrdersMapper {

    companion object {

        fun mapOrdersNetwork(orders: List<OrderNetwork>): List<Order> {
            val mappedOrders = ArrayList<Order>()
            orders.forEach { order ->
                mappedOrders.add(mapOrder(order))
            }
            return mappedOrders
        }

        fun mapOrder(order: OrderNetwork): Order {
            if (order.orderTimeStart == null)
                order.orderTimeStart = 0
            if (order.orderTimeFinish == null)
                order.orderTimeFinish = 0
            val orderTimeStart = DateMapper.mapDateTime(order.orderTimeStart!!)
            val orderTimeFinish = DateMapper.mapDateTime(order.orderTimeFinish!!)
            val routePoints = mapOrderRoutePoints(order.orderRoutepoints)

            return Order(order.orderId, orderTimeStart, orderTimeFinish, order.orderWorkTime, getUtf8String(order.startRoutePointAddress), getUtf8String(order.finalRoutePointAddress), order.orderPrice, order.orderStatus, routePoints)
        }

        private fun mapOrderRoutePoints(routePoints: List<OrderRoutePointNetwork>): List<OrderRoutePoint> {
            val mappedPoints = ArrayList<OrderRoutePoint>()
            routePoints.forEach { 
                mappedPoints.add(mapOrderRoutePoint(it))
            }
            return mappedPoints
        }

        private fun mapOrderRoutePoint(routePoint: OrderRoutePointNetwork): OrderRoutePoint {
            val mappedPoint = OrderRoutePoint()
            mappedPoint.routepointId = routePoint.routepointId
            mappedPoint.routepointNum = routePoint.routepointNum
            mappedPoint.routepointNum = routePoint.routepointNum
            mappedPoint.routepointId = routePoint.routepointId
            mappedPoint.routepointNum = routePoint.routepointNum
            mappedPoint.routepointOrderId = routePoint.routepointOrderId
            mappedPoint.routepointLocation= routePoint.routepointLocation
            mappedPoint.routepointContactPerson = getUtf8String(routePoint.routepointContactPerson)
            mappedPoint.routepointContactCompany = getUtf8String(routePoint.routepointContactCompany)
            mappedPoint.routepointContactPhone = routePoint.routepointContactPhone
            mappedPoint.routepointContactAddress = getUtf8String(routePoint.routepointContactAddress)
            mappedPoint.routepointNeedLoadHelp = routePoint.routepointNeedLoadHelp
            mappedPoint.routepointNeedLoader = routePoint.routepointNeedLoader
            mappedPoint.routepointLoadHere = routePoint.routepointLoadHere
            mappedPoint.routepointUnloadHere = routePoint.routepointUnloadHere
            mappedPoint.routepointPaidEnter = routePoint.routepointPaidEnter
            mappedPoint.routepointCurtainsOut = routePoint.routepointCurtainsOut
            mappedPoint.routepointComment = routePoint.routepointComment
            return mappedPoint
        }
        
        private fun getUtf8String(text: String?): String {
            if (text == null)
                return ""
            return String(text.toByteArray(Charsets.UTF_8), Charsets.UTF_8)
        }
    }
}