package v_aniskin.com.trucktaxi.presentation.models

import v_aniskin.com.trucktaxi.domain.models.ResponseMonade

/**
 * Created by root on 25.08.17.
 */
class ModelContainer<T>(var mData: T?, error: String?, status: String?): ResponseMonade(error, status) {

    constructor(error: String?, status: String?): this(null, error, status)
}