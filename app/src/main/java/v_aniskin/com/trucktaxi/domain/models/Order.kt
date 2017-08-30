package v_aniskin.com.trucktaxi.domain.models

import java.util.*

/**
 * Created by root on 27.05.17.
 */
class Order {

    companion object {

        const val STATE_ITEM: Int = 0
        const val STATE_HEADER: Int = 1
    }

    var orderId: String? = null

    var orderTimeStart: String? = null
    var orderTimeFinish: String? = null
    var orderWorkTime: String? = null

    var startRoutePointAddress: String? = null
    var finalRoutePointAddress: String? = null

    var orderStatus: String? = null
    var orderPrice: String? = null

    var orderRoutepoints: List<OrderRoutePoint> = Collections.emptyList()

    var desc: String? = null
    var state: Int? = STATE_ITEM

    constructor(desc: String, state: Int?) {
        this.desc = desc
        this.state = state
    }

    constructor(orderId: String?, orderTimeStart: String?, orderTimeFinish: String?, orderWorkTime: String?,
                startRoutePointAddress: String?, finalRoutePointAddress: String?, orderPrice: String?, orderStatus: String?,
                orderRoutepoints: List<OrderRoutePoint>) {
        this.orderId = orderId
        this.orderTimeStart = orderTimeStart
        this.orderTimeFinish = orderTimeFinish
        this.orderWorkTime = orderWorkTime
        this.startRoutePointAddress = startRoutePointAddress
        this.finalRoutePointAddress = finalRoutePointAddress
        this.orderPrice = orderPrice
        this.orderStatus = orderStatus
        this.orderRoutepoints = orderRoutepoints

        this.desc = "Заказ #"+orderId+" на "+orderTimeStart+" "+finalRoutePointAddress+" Часы работы "+orderWorkTime+" - "+ orderPrice +" руб."
    }
}