package v_aniskin.com.trucktaxi.data.network_client.responses

import v_aniskin.com.trucktaxi.data.network_client.models.PaymentNetwork


/**
 * Created by root on 16.09.17.
 */
class PaymentResponse: BaseResponse() {

    var paymentData: PaymentNetwork = PaymentNetwork()
}