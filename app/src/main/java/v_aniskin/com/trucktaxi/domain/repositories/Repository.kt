package v_aniskin.com.trucktaxi.domain.repositories

import rx.Observable
import v_aniskin.com.trucktaxi.data.network_client.requests.AuthRequest
import v_aniskin.com.trucktaxi.data.network_client.responses.*

/**
 * Created by root on 24.05.17.
 */

interface Repository {

    fun auth(authRequest: AuthRequest): Observable<AuthResponse>

    fun getToken(): String

    fun putToken(token: String)

    fun removeToken()

    fun updateToken(): Observable<BaseResponse>

    fun getProfile(): Observable<ProfileResponse>

    fun getNotifications(): Observable<NotificationsResponse>

    fun getAllOrders(): Observable<OrdersResponse>

    fun startScanLocation()

    fun stopScanLocation()

    fun getAppointedOrders(): Observable<OrdersResponse>

    fun getPreappointedOrders(): Observable<OrdersResponse>

    fun getNewOrders(): Observable<OrdersResponse>

    fun getCurrentOrders(): Observable<OrdersResponse>

    fun getCompleteOrders(): Observable<OrdersResponse>

    fun getArchiveOrders(): Observable<OrdersResponse>

    fun getPaymentsFuture(): Observable<PaymentsResponse>

    fun getPaymentsComlete(): Observable<PaymentsResponse>

    fun setWorkState(state: String): Observable<ChangeWorkStateResponse>
}
