package v_aniskin.com.trucktaxi.data.network_client

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable
import v_aniskin.com.trucktaxi.data.network_client.requests.AuthRequest
import v_aniskin.com.trucktaxi.data.network_client.responses.AuthResponse

/**
 * Created by root on 25.06.17.
 */
interface ApiController {

    @Headers("Content-Type: application/json")
    @POST(AuthRequest.URI)
    fun auth(@Body body: AuthRequest): Observable<AuthResponse>
}