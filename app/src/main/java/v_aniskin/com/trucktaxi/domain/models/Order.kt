package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 27.05.17.
 */
class Order {

    companion object {

        const val STATE_ITEM: Int = 0
        const val STATE_HEADER: Int = 1
    }

    var order: String? = null
    var beginDate: String? = null
    var address: String? = null
    var workTime: String? = null
    var desc: String? = null
    var state: Int? = STATE_ITEM
    var status: String? = null
    var cost: String? = null

    constructor(desc: String, state: Int?) {
        this.desc = desc
        this.state = state
    }

    constructor(order: String?, beginDate: String?, address: String?, workTime: String?, status: String?, cost: String?) {
        this.order = order
        this.beginDate = beginDate
        this.address = address
        this.workTime = workTime
        this.status = status
        this.cost = cost
        this.desc = "Заказ #"+order+" на "+beginDate+" "+address+" Часы работы "+workTime+" - "+cost+" руб."
    }
}