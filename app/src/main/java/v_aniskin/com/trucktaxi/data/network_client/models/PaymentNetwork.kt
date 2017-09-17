package v_aniskin.com.trucktaxi.data.network_client.models


/**
 * Created by root on 08.08.17.
 */
class PaymentNetwork {

    var order: String? = null
    var time: String? = null
    var sum: String? = null
    var status: String? = null
    var desc: String? = null
    var desc2: String? = null

    constructor()

    constructor(order: String?, time: String?, sum: String?, status: String?) {
        this.order = order
        this.time = time
        this.sum = sum
        this.status = status
    }
}