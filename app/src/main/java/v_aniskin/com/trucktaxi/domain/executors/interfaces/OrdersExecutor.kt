package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent

/**
 * Created by root on 25.07.17.
 */
interface OrdersExecutor {

    fun getOrders(): Observable<ModelsContainer<OrderPresent>>
}