package v_aniskin.com.trucktaxi.presentation.models

/**
 * Created by root on 28.05.17.
 */
class OrderPresent {

    companion object {

        const val STATE_CURRENT: Int = 0
        const val STATE_NEW: Int = 1
        const val STATE_HISTORY: Int = 2
        const val STATE_HEADER: Int = 3
    }

    var order: String? = null
    var time: String? = null
    var address: String? = null
    var workTime: String? = null
    var desc: String? = null
    var state: Int? = null

    constructor(desc: String) {
        this.desc = desc
    }

    constructor(order: String?, time: String?, address: String?, workTime: String?, state: Int?) {
        this.order = order
        this.time = time
        this.address = address
        this.workTime = workTime
        this.state = state
    }
}