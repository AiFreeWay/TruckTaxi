package v_aniskin.com.trucktaxi.data.network_client

import retrofit2.http.*
import rx.Observable
import v_aniskin.com.trucktaxi.data.network_client.requests.AuthRequest
import v_aniskin.com.trucktaxi.data.network_client.requests.CheckTokenRequest
import v_aniskin.com.trucktaxi.data.network_client.requests.ProfileRequest
import v_aniskin.com.trucktaxi.data.network_client.responses.AuthResponse
import v_aniskin.com.trucktaxi.data.network_client.responses.BaseResponse
import v_aniskin.com.trucktaxi.data.network_client.responses.ProfileResponse

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
}