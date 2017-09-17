package v_aniskin.com.trucktaxi.domain.executors.interfaces

import rx.Observable
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer

/**
 * Created by root on 25.07.17.
 */
interface OrdersExecutor {

    fun getFeatureOrders(): Observable<ModelContainer<List<Order>>>
    fun getCurrentOrders(): Observable<ModelContainer<List<Order>>>
    fun getHistoryOrders(): Observable<ModelContainer<List<Order>>>
    fun getOrderDetail(orderId: String): Observable<ModelContainer<Order>>
    fun getRoutePoints(orderId: String): Observable<*>
}