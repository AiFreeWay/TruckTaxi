package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 25.07.17.
 */

class OrdersRequest(token: String, var orderStatus: String): BaseRequest(token) {

    companion object {
        const val URI: String = "api/orders/getList/index.php"
        const val FIELD_TOKEN: String = "token"
        const val FIELD_LIST_TYPE: String = "listType"
        const val FIELD_ORDER_STATUS: String = "orderStatus"

        const val LIST_TYPE_SHORT: String = "short"

        const val ORDER_STATUS_ALL: String = "all"
        const val ORDER_STATUS_CURRENT: String = "current"
        const val ORDER_STATUS_NEW: String = "new"
        const val ORDER_STATUS_HISTORY: String = "history"
    }
}
