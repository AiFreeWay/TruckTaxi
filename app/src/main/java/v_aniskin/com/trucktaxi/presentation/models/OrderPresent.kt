package v_aniskin.com.trucktaxi.presentation.models

/**
 * Created by root on 28.05.17.
 */
class OrderPresent {

    companion object {

        const val STATE_ITEM: Int = 0
        const val STATE_HEADER: Int = 1
    }

    var order: String? = null
    var time: String? = null
    var address: String? = null
    var workTime: String? = null
    var desc: String? = null
    var state: Int? = STATE_ITEM
    var status: String? = null

    constructor(desc: String, state: Int?) {
        this.desc = desc
        this.state = state
    }

    constructor(desc: String?, order: String?, time: String?, address: String?, workTime: String?, state: Int?, status: String?) {
        this.desc = desc
        this.order = order
        this.time = time
        this.address = address
        this.workTime = workTime
        this.state = state
        this.status = status
    }
}