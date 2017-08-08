package v_aniskin.com.trucktaxi.data.network_client.responses

import v_aniskin.com.trucktaxi.data.network_client.models.PaymentNetwork
import java.util.*

/**
 * Created by root on 08.08.17.
 */
class PaymentsResponse: BaseResponse() {

    var payments: List<PaymentNetwork> = Collections.emptyList()
}