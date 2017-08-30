package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 26.08.17.
 */
class OrderRoutePoint() {

    var routepointId: String? = null
    var routepointNum: String? = null
    var routepointOrderId: String? = null
    var routepointLocation: Array<Float> = arrayOf()
    var routepointContactPerson: String? = null
    var routepointContactCompany: String? = null
    var routepointContactPhone: String? = null
    var routepointContactAddress: String? = null
    var routepointNeedLoadHelp: String? = null
    var routepointNeedLoader: String? = null
    var routepointLoadHere: String? = null
    var routepointUnloadHere: String? = null
    var routepointPaidEnter: String? = null
    var routepointCurtainsOut: String? = null
    var routepointComment: String? = null
}