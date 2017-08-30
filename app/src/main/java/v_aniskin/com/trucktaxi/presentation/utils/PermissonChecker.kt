package v_aniskin.com.trucktaxi.presentation.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * Created by root on 25.08.17.
 */
class PermissonChecker {

    companion object {

        fun checkPermission(permission: String, activity: Activity, requestCode: Int): Boolean {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(activity, permission) &&
                    PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(activity, permission)) {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
                return false
            }
            return true
        }
    }
}