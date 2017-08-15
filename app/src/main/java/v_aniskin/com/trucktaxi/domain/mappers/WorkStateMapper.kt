package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.responses.EditProfileResponse
import v_aniskin.com.trucktaxi.domain.models.WorkState

/**
 * Created by root on 15.08.17.
 */
class WorkStateMapper {

    companion object {

        fun mapWorkState(response: EditProfileResponse): WorkState {
            return WorkState(response.state, response.error, response.status)
        }
    }
}