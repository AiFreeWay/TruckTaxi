package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 08.08.17.
 */
class PaymentsRequest(token: String): BaseRequest(token) {

    companion object {
        const val URI: String = "api/checklistitems/getList/index.php"

        const val FIELD_TOKEN: String = "token"
        const val FIELD_LIST_TYPE: String = "listType"
        const val FIELD_ORDER_STATUS: String = "orderStatus"
        const val LIST_TYPE_SHORT: String = "short"
        const val ORDER_STATUS_ALL: String = "all"
    }
}