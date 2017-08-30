package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import java.util.*

/**
 * Created by root on 08.08.17.
 */
interface PaymentsExecutor {

    fun getPayments(): Observable<ModelContainer<List<Payment>>>
}