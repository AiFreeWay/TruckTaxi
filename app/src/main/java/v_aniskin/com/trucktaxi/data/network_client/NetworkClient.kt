package v_aniskin.com.trucktaxi.data.network_client

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.Observable
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.requests.*
import v_aniskin.com.trucktaxi.data.network_client.responses.*
import java.io.File


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

    fun editProfile(request: EditProfileRequest): Observable<EditProfileResponse> {
        return mApiController.editProfile(request.token, request.workState)
    }

    fun getOrder(request: OrderRequest): Observable<OrderResponse> {
        return mApiController.getOrder(request.token, request.orderId)
    }

    fun loadImage(imageType: Int, file: File): Observable<BaseResponse> {
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile)
        val name = RequestBody.create(MediaType.parse("text/plain"), "upload_test")
        return mApiController.postImage(body, name)
    }

    fun getRoutePoints(locationRequest: LocationRequest): Observable<LocationResponse> {
        return mApiController.getRoutePoints(locationRequest.token, locationRequest.orderId)
    }
}