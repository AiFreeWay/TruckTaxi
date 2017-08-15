package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.models.PaymentNetwork
import v_aniskin.com.trucktaxi.data.network_client.responses.PaymentsResponse
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.PaymentPresent
import java.util.*

/**
 * Created by root on 08.08.17.
 */
class PaymentsMapper {

    companion object {

        fun mapPaymentsNetwork(payments: List<PaymentNetwork>): List<Payment> {
            val mappedPayments = ArrayList<Payment>()
            payments.forEach { paymentNetwork ->
                mappedPayments.add(mapPaymentNetwork(paymentNetwork))
            }
            return mappedPayments
        }

        fun mapPayments(responseContainer: ModelsContainer<Payment>): ModelsContainer<PaymentPresent> {
            val mappedContainer: ModelsContainer<PaymentPresent> = ModelsContainer(responseContainer.error, responseContainer.status)
            responseContainer.mData.forEach { payment ->
                mappedContainer.mData.add(mapPayment(payment))
            }
            return mappedContainer
        }

        fun mapPaymentNetwork(payment: PaymentNetwork): Payment {
            return Payment(payment.order, payment.time, payment.sum, payment.status)
        }

        fun mapPayment(payment: Payment): PaymentPresent {
            return PaymentPresent(payment.order, payment.time, payment.sum, payment.status, payment.state)
        }
    }
}