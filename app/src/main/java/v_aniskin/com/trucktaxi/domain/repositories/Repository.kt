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

    fun getCurrentOrders(): Observable<OrdersResponse>

    fun getNewOrders(): Observable<OrdersResponse>

    fun getHistoryOrders(): Observable<OrdersResponse>

    fun startScanLocation()

    fun stopScanLocation()
}
