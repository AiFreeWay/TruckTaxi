package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 08.08.17.
 */
class PaymentsRequest(token: String, var paymentStatus: String ): BaseRequest(token) {

    companion object {
        const val URI: String = "api/checklistitems/getList/index.php"
        const val FIELD_PAYMENT_STATUS: String = "paymentStatus"
        const val FIELD_TOKEN: String = "token"
    }
}