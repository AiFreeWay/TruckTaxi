package v_aniskin.com.trucktaxi.data.network_client

import retrofit2.http.*
import rx.Observable
import v_aniskin.com.trucktaxi.data.network_client.requests.AuthRequest
import v_aniskin.com.trucktaxi.data.network_client.responses.AuthResponse

/**
 * Created by root on 25.06.17.
 */
interface ApiController {

    @FormUrlEncoded
    @POST(AuthRequest.URI)
    fun auth(@Field (AuthRequest.FIELD_USER_MAIL) userMail: String,
             @Field (AuthRequest.FIELD_USER_PASSWORD) userPassword: String,
             @Field (AuthRequest.FIELD_USER_TYPE) userType: String): Observable<AuthResponse>
}