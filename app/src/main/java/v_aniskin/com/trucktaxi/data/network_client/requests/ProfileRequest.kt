package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 22.07.17.
 */

class ProfileRequest(token: String): BaseRequest(token) {

    companion object {
        const val URI: String = "api/user/getMe/index.php"
        const val FIELD_TOKEN: String = "token"
    }
}
