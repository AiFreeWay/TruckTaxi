package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 27.05.17.
 */
class Payment {

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

    constructor(order: String?, time: String?, sum: String?, status: String?) {
        this.order = order
        this.time = time
        this.sum = sum
        this.status = status
    }
}