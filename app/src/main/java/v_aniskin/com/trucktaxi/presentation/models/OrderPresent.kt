package v_aniskin.com.trucktaxi.presentation.models

/**
 * Created by root on 28.05.17.
 */
class OrderPresent {

    var order: String? = null
    var time: String? = null
    var address: String? = null
    var workTime: String? = null
    var desc: String? = null

    constructor(desc: String) {
        this.desc = desc
    }

    constructor(order: String?, time: String?, address: String?, workTime: String?) {
        this.order = order
        this.time = time
        this.address = address
        this.workTime = workTime
    }
}