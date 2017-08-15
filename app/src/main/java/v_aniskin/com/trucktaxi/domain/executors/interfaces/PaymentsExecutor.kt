package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.PaymentPresent

/**
 * Created by root on 08.08.17.
 */
interface PaymentsExecutor {

    fun getPayments(): Observable<ModelsContainer<PaymentPresent>>
}