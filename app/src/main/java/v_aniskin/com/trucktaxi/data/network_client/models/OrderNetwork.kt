package v_aniskin.com.trucktaxi.data.network_client.models

/**
 * Created by root on 08.08.17.
 */
class OrderNetwork {

    var orderId: String? = null
    var orderTimeStart: Long? = null
    var orderTimeFinish: Long? = null
    var address: String? = null
    var orderStatus: String? = null
    var orderPrice: String? = null

    constructor()

    constructor(orderId: String?, orderTimeStart: Long?, orderTimeFinish: Long?, address: String?, orderStatus: String?, orderPrice: String?) {
        this.orderId = orderId
        this.orderTimeStart = orderTimeStart
        this.orderTimeFinish = orderTimeFinish
        this.address = address
        this.orderStatus = orderStatus
        this.orderPrice = orderPrice
    }
}