package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 25.08.17.
 */
class OrderRequest(token: String, var orderId: String): BaseRequest(token) {

    companion object {
        const val URI: String = "api/orders/get/index.php"
        const val FIELD_TOKEN: String = "token"
        const val FIELD_ORDER_ID: String = "orderId"
    }
}