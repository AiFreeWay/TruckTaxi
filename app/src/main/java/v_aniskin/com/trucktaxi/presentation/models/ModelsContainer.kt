package v_aniskin.com.trucktaxi.presentation.models

import v_aniskin.com.trucktaxi.domain.models.ResponseMonade
import java.util.*

/**
 * Created by root on 24.07.17.
 */
class ModelsContainer<T>(error: String?, status: String?): ResponseMonade(error, status) {

    var mData: List<T> = Collections.emptyList()

}