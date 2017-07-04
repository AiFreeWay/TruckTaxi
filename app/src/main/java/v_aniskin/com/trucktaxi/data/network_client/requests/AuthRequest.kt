package v_aniskin.com.trucktaxi.data.network_client.requests

/**
 * Created by root on 04.07.17.
 */
class AuthRequest(var userEmail: String, var userPassword: String) {

    companion object {
        const val URI: String = "api/sessions/new/index.php"
    }

    val userType: String = "driver";
}