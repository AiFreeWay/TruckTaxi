package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 15.08.17.
 */
class WorkState(var state: String, error: String?, status: String?) : ResponseMonade(error, status) {
}