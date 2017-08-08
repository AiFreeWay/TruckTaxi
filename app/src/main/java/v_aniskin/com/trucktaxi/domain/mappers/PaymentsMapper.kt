package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.models.PaymentNetwork
import v_aniskin.com.trucktaxi.data.network_client.responses.PaymentsResponse
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.PaymentPresent

/**
 * Created by root on 08.08.17.
 */
class PaymentsMapper {

    companion object {

        fun mapPayments(response: PaymentsResponse): ModelsContainer<PaymentPresent> {
            val mappedContainer: ModelsContainer<PaymentPresent> = ModelsContainer(response.error, response.status)
            response.payments.forEach { paymentNetwork ->
                val payment = mapPaymentNetwork(paymentNetwork)
                mappedContainer.mData.add(mapPayment(payment))
            }
            return mappedContainer
        }

        fun mapPaymentNetwork(payment: PaymentNetwork): Payment {
            return Payment("", "")
        }

        fun mapPayment(payment: Payment): PaymentPresent {
            return PaymentPresent("", "")
        }
    }
}