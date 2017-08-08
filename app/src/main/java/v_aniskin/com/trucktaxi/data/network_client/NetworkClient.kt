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

    fun auth(authRequest: AuthRequest): Observable<AuthResponse> {
        return  mApiController.auth(authRequest.userEmail, authRequest.userPassword, authRequest.userType)
    }

    fun checkToken(checkTokenRequest: CheckTokenRequest): Observable<BaseResponse> {
        return  mApiController.checkToken(checkTokenRequest.token)
    }

    fun getProfile(profileRequest: ProfileRequest): Observable<ProfileResponse> {
        return  mApiController.getProfile(profileRequest.token)
    }

    fun getNotifications(notificationsRequest: NotificationsRequest): Observable<NotificationsResponse> {
        val response: NotificationsResponse = NotificationsResponse()
        response.status = "ok"
        return Observable.just(response)
    }

    fun getOrders(ordersRequest: OrdersRequest): Observable<OrdersResponse> {
        return mApiController.getOrders(ordersRequest.token, OrdersRequest.LIST_TYPE_SHORT, ordersRequest.orderStatus)
    }

    fun getPayments(paymenstRequest: PaymentsRequest): Observable<PaymentsResponse> {
        return mApiController.getPayments(paymenstRequest.token)
    }
}