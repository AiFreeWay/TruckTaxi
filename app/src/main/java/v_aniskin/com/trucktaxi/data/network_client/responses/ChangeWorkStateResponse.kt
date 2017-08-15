package v_aniskin.com.trucktaxi.data.network_client.responses

import v_aniskin.com.trucktaxi.application.utils.WorkStates

/**
 * Created by root on 15.08.17.
 */
class ChangeWorkStateResponse: BaseResponse() {

    var state: Int = WorkStates.WORK_STATE_ON_REST
}