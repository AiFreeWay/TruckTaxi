package v_aniskin.com.trucktaxi.presentation.models

/**
 * Created by root on 28.05.17.
 */
class PaymentPresent {

    var order: String? = null
    var time: String? = null
    var sum: String? = null
    var state: String? = null
    var desc: String? = null
    var desc2: String? = null

    constructor(desc: String, desc2: String) {
        this.desc = desc
        this.desc2 = desc2
    }

    constructor(order: String?, time: String?, sum: String?, state: String?) {
        this.order = order
        this.time = time
        this.sum = sum
        this.state = state
    }
}