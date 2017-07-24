package v_aniskin.com.trucktaxi.application.utils

import android.content.Context
import v_aniskin.com.trucktaxi.R

/**
 * Created by root on 17.07.17.
 */

class NetworkErrors {

    companion object {
        const val BAD_LOGIN_AUTH: String = "BAD_DRIVEREMAIL"
        const val BAD_PASSWORD_AUTH: String = "INVALID_PASSWORD"
        const val NEED_INPUT_PARAMS: String = "NEED_INPUT_PARAMS"
        const val BAD_SESSION_ID: String = "BAD_SESSION_ID"

        fun getErrorMessageByType(context: Context, error: String?) : String {
            if (error == BAD_LOGIN_AUTH || error == BAD_PASSWORD_AUTH)
                return context.getString(R.string.bad_auth)
            else if (error == NEED_INPUT_PARAMS)
                return context.getString(R.string.please_fill_fields)
            else if (error == BAD_SESSION_ID)
                return context.getString(R.string.invalid_token)
            return ""
        }
    }
}