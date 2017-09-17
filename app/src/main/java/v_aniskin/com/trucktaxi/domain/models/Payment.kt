package v_aniskin.com.trucktaxi.domain.models


/**
 * Created by root on 27.05.17.
 */
class Payment {

    var id: String? = null

    //Holder
    var order: String? = null
    var time: String? = null
    var sum: String? = null
    var status: String? = null

    var desc: String? = null
    var desc2: String? = null
    var state: Int? = ListItemTypes.TYPE_ITEM

    //Detail
     var beginWork: String? = null
     var endWork: String? = null
     var allWorkedHours: String? = null
     var count: String? = null
     var minimalTarif: String? = null
     var hoursCost: String? = null
     var mkadPass: String? = null
     var ttkPass: String? = null
     var kmCountValue: String? = null
     var costMkadHours: String? = null
     var kmSum: String? = null
     var totalOnTarif: String? = null
     var fines: String? = null
     var payedCustomer: String? = null
     var payedCommissions: String? = null
     var payedTotalPay: String? = null
    
    //Order Fragment
    var allHours: String? = null
    var workedHours: String? = null
    var supportHours: String? = null
    var kmCost: String? = null
    var entry: String? = null
    var loadingHours: String? = null
    var rastentovka: String? = null
    var total: String? = null
    
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