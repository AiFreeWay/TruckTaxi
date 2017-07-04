package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.requests.AuthRequest
import v_aniskin.com.trucktaxi.data.network_client.responses.AuthResponse
import v_aniskin.com.trucktaxi.domain.models.Auth

/**
 * Created by root on 04.07.17.
 */
class UsersMapper {

    companion object {

        fun mapAuth(id: String, password: String): AuthRequest {
            return AuthRequest(id, password)
        }

        fun mapAuth(response: AuthResponse): Auth {
            return Auth(response.token, response.error, response.status)
        }
    }
}