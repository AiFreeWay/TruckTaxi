package v_aniskin.com.trucktaxi.presentation.models

/**
 * Created by root on 02.06.17.
 */
class ChatPresent(var type: Int, var text: String, var date: String) {

    companion object {

        const val STATE_ME: Int = 0
        const val STATE_COMPANION: Int = 1
    }
}