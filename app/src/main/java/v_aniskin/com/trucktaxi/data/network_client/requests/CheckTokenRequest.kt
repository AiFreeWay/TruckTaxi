package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 20.07.17.
 */

class CheckTokenRequest(var token: String) {

    companion object {
        const val URI: String = "api/sessions/terminate/index.php"
        const val FIELD_TOKEN: String = "token"
    }
}
