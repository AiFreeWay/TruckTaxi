package v_aniskin.com.trucktaxi.data.network_client

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.GsonBuilder
import rx.Observable
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.requests.*
import v_aniskin.com.trucktaxi.data.network_client.responses.*
import v_aniskin.com.trucktaxi.domain.models.ResponseMonade


/**
 * Created by root on 25.06.17.
 */

class NetworkClient {

    private val API_URL = "http://app.tg-group.ru/"

    private var mApiController: ApiController

    init {
        Logger.testLog("NetworkClient Create")
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        mApiController = retrofit.create(ApiController::class.java)
    }

    fun auth(request: AuthRequest): Observable<AuthResponse> {
        return  mApiController.auth(request.userEmail, request.userPassword, request.userType)
    }

    fun checkToken(request: CheckTokenRequest): Observable<BaseResponse> {
        return  mApiController.checkToken(request.token)
    }

    fun getProfile(request: ProfileRequest): Observable<ProfileResponse> {
        return  mApiController.getProfile(request.token)
    }

    fun getNotifications(request: NotificationsRequest): Observable<NotificationsResponse> {
        val response: NotificationsResponse = NotificationsResponse()
        response.status = "ok"
        return Observable.just(response)
    }

    fun getOrders(request: OrdersRequest): Observable<OrdersResponse> {
        return mApiController.getOrders(request.token, OrdersRequest.LIST_TYPE_SHORT, request.orderStatus)
    }

    fun getPayments(request: PaymentsRequest): Observable<PaymentsResponse> {
        return mApiController.getPayments(request.token, request.paymentStatus)
    }

    fun setWorkState(request: ChangeWorkStateRequest): Observable<ChangeWorkStateResponse> {
        return mApiController.changeWorkState(request.token, request.workState)
    }
}