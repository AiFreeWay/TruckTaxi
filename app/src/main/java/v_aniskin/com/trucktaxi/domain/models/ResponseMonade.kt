package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 04.07.17.
 */
open class ResponseMonade(var error: String?, var status: String?) {

    companion object {
        const val SUCCESS: String = "ok"
        const val ERROR: String = "error"
    }
}