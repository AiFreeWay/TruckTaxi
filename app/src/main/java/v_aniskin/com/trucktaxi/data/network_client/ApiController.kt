package v_aniskin.com.trucktaxi.data.network_client

import retrofit2.http.*
import rx.Observable
import v_aniskin.com.trucktaxi.data.network_client.requests.*
import v_aniskin.com.trucktaxi.data.network_client.responses.*
import okhttp3.RequestBody
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Multipart



/**
 * Created by root on 25.06.17.
 */
interface ApiController {

    @FormUrlEncoded
    @POST(AuthRequest.URI)
    fun auth(@Field (AuthRequest.FIELD_USER_MAIL) userMail: String,
             @Field (AuthRequest.FIELD_USER_PASSWORD) userPassword: String,
             @Field (AuthRequest.FIELD_USER_TYPE) userType: String): Observable<AuthResponse>

    @FormUrlEncoded
    @POST(CheckTokenRequest.URI)
    fun checkToken(@Field (CheckTokenRequest.FIELD_TOKEN) token: String): Observable<BaseResponse>

    @FormUrlEncoded
    @POST(ProfileRequest.URI)
    fun getProfile(@Field (ProfileRequest.FIELD_TOKEN) token: String): Observable<ProfileResponse>

    @FormUrlEncoded
    @POST(OrdersRequest.URI)
    fun getOrders(@Field (OrdersRequest.FIELD_TOKEN) token: String,
                  @Field (OrdersRequest.FIELD_LIST_TYPE) listType: String,
                  @Field (OrdersRequest.FIELD_ORDER_STATUS) orderStatus: String): Observable<OrdersResponse>

    @FormUrlEncoded
    @POST(PaymentsRequest.URI)
    fun getPayments(@Field (PaymentsRequest.FIELD_TOKEN) token: String,
                    @Field (PaymentsRequest.FIELD_PAYMENT_STATUS) paymentStatus: String): Observable<PaymentsResponse>

    @FormUrlEncoded
    @POST(EditProfileRequest.URI)
    fun editProfile(@Field (EditProfileRequest.FIELD_TOKEN) token: String,
                        @Field (EditProfileRequest.FIELD_DRIVER_STATUS) driverStatus: String): Observable<EditProfileResponse>

    @FormUrlEncoded
    @POST(OrderRequest.URI)
    fun getOrder(@Field (OrderRequest.FIELD_TOKEN) token: String,
                  @Field (OrderRequest.FIELD_ORDER_ID) orderId: String): Observable<OrderResponse>

    @Multipart
    @POST("/")
    fun postImage(@Part image: MultipartBody.Part, @Part("name") name: RequestBody): Observable<BaseResponse>

    @GET(LocationRequest.URI)
    fun getRoutePoints(@Query (LocationRequest.FIELD_TOKEN) token: String,
                       @Query (LocationRequest.FIELD_ORDER_ID) listType: String): Observable<LocationResponse>
}