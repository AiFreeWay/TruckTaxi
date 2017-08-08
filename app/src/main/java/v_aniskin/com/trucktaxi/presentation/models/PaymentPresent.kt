package v_aniskin.com.trucktaxi.presentation.models

import v_aniskin.com.trucktaxi.domain.models.ListItemTypes

/**
 * Created by root on 28.05.17.
 */
class PaymentPresent {

    var order: String? = null
    var time: String? = null
    var sum: String? = null
    var status: String? = null
    var desc: String? = null
    var desc2: String? = null
    var state: Int? = ListItemTypes.TYPE_ITEM

    constructor(desc: String, desc2: String, state: Int) {
        this.desc = desc
        this.desc2 = desc2
        this.state = state
    }

    constructor(order: String?, time: String?, sum: String?, status: String?, state: Int?) {
        this.order = order
        this.time = time
        this.sum = sum
        this.status = status
        this.state = state
    }
}