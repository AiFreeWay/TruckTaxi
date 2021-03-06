package v_aniskin.com.trucktaxi.data.repository

import android.content.Context
import rx.Observable
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.OrdersTypes
import v_aniskin.com.trucktaxi.application.utils.PaymentsType
import v_aniskin.com.trucktaxi.data.HawkController
import v_aniskin.com.trucktaxi.data.network_client.NetworkClient
import v_aniskin.com.trucktaxi.data.network_client.requests.*
import v_aniskin.com.trucktaxi.data.network_client.responses.*
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import java.io.File
import javax.inject.Inject

/**
 * Created by root on 24.05.17.
 */
class RepositoryImpl @Inject constructor(context: Context) : Repository {

    private val mNetworkClinet: NetworkClient
    private val mHawkController: HawkController

    init {
        Logger.testLog("RepositoryImpl Create")
        mNetworkClinet = NetworkClient()
        mHawkController = HawkController()
    }

    override fun auth(authRequest: AuthRequest): Observable<AuthResponse> {
        return mNetworkClinet.auth(authRequest)
    }

    override fun getToken(): String {
        return mHawkController.getString(HawkController.TOKEN)
    }

    override fun putToken(token: String) {
        mHawkController.putString(HawkController.TOKEN, token)
    }

    override fun updateToken(): Observable<BaseResponse> {
        return mNetworkClinet.checkToken(CheckTokenRequest(getToken()))
    }

    override fun removeToken() {
        mHawkController.putString(HawkController.TOKEN, null)
    }

    override fun getProfile(): Observable<ProfileResponse> {
        return mNetworkClinet.getProfile(ProfileRequest(getToken()))
    }

    override fun getNotifications(): Observable<NotificationsResponse> {
        return mNetworkClinet.getNotifications(NotificationsRequest(getToken()))
    }

    override fun getAllOrders(): Observable<OrdersResponse> {
        return mNetworkClinet.getOrders(OrdersRequest(getToken(), OrdersRequest.ORDER_STATUS_ALL))
    }

    override fun getPreappointedOrders(): Observable<OrdersResponse> {
        return mNetworkClinet.getOrders(OrdersRequest(getToken(), OrdersTypes.ORDER_STATUS_PREAPPOINTED))
    }

    override fun getNewOrders(): Observable<OrdersResponse> {
        return mNetworkClinet.getOrders(OrdersRequest(getToken(), OrdersTypes.ORDER_STATUS_NEW))
    }

    override fun getAppointedOrders(): Observable<OrdersResponse> {
        return mNetworkClinet.getOrders(OrdersRequest(getToken(), OrdersTypes.ORDER_STATUS_APPOINTED))
    }

    override fun getCurrentOrders(): Observable<OrdersResponse> {
        return mNetworkClinet.getOrders(OrdersRequest(getToken(), OrdersTypes.ORDER_STATUS_CURRENT))
    }

    override fun getArchiveOrders(): Observable<OrdersResponse> {
        return mNetworkClinet.getOrders(OrdersRequest(getToken(), OrdersTypes.ORDER_STATUS_ARCHIVE))
    }

    override fun getCompleteOrders(): Observable<OrdersResponse> {
        return mNetworkClinet.getOrders(OrdersRequest(getToken(), OrdersTypes.ORDER_STATUS_COMPLETE))
    }

    override fun getPaymentsFuture(): Observable<PaymentsResponse> {
        return mNetworkClinet.getPayments(PaymentsRequest(getToken(), PaymentsType.ORDER_STATUS_FUTURE))
    }

    override fun getPaymentsComlete(): Observable<PaymentsResponse> {
        return mNetworkClinet.getPayments(PaymentsRequest(getToken(), PaymentsType.ORDER_STATUS_COMPLETE))
    }

    override fun editProfile(state: String): Observable<EditProfileResponse> {
        return mNetworkClinet.editProfile(EditProfileRequest(getToken(), state))
    }

    override fun getOrder(orderId: String): Observable<OrderResponse> {
        return mNetworkClinet.getOrder(OrderRequest(getToken(), orderId))
    }

    override fun getPayment(paymentId: String, paymentType: String): Observable<PaymentResponse> {
        return Observable.just(PaymentResponse())
    }

    override fun loadImage(imageType: Int, file: File): Observable<BaseResponse> = mNetworkClinet.loadImage(imageType, file)

    override fun getRoutePoints(orderId: String): Observable<LocationResponse> {
        return mNetworkClinet.getRoutePoints(LocationRequest(getToken(), orderId))
    }
}