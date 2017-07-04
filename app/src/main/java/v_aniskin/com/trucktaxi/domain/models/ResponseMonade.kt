package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 04.07.17.
 */
open class ResponseMonade(var status: String?, var error: String?) {

    companion object {
        const val SUCCESS: String = "success"
        const val ERROR: String = "error"
    }
}