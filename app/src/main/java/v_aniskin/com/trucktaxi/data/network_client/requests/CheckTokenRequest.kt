package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 19.07.17.
 */

class CheckTokenRequest(token: String): BaseRequest(token) {

    companion object {
        const val URI: String = "api/sessions/check/index.php"
        const val FIELD_TOKEN: String = "token"
    }
}
