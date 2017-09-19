package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 17.09.17.
 */
class LocationRequest(token: String, var orderId: String): BaseRequest(token) {

    companion object {
        const val URI: String = "api/georoutes/get/"
        const val FIELD_TOKEN: String = "token"
        const val FIELD_ORDER_ID: String = "orderId"
    }
}