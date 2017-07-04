package v_aniskin.com.trucktaxi.data

import com.orhanobut.hawk.Hawk

/**
 * Created by root on 04.07.17.
 */

class HawkController {

    companion object {
        const val TOKEN: String = "token"
    }

    fun getString(key: String): String {
        return Hawk.get(key, "")
    }

    fun putString(key: String, data: String) {
        Hawk.put(key, data)
    }
}
