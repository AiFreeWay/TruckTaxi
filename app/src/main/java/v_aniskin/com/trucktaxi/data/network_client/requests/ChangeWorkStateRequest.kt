package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 15.08.17.
 */
class ChangeWorkStateRequest(token: String, var workState: String): BaseRequest(token) {

    companion object {
        const val URI: String = "api/driver/edit/index.php"
        const val FIELD_TOKEN: String = "token"
        const val FIELD_DRIVER_STATUS: String = "driverStatus"
    }
}