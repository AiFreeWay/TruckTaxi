package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 04.07.17.
 */

class Auth(var token: String?, error: String?, status: String?) : ResponseMonade(error, status) {

}
