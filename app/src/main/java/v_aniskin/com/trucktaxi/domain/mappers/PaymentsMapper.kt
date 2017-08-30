package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.models.PaymentNetwork
import v_aniskin.com.trucktaxi.domain.models.Payment
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

        fun mapPaymentNetwork(payment: PaymentNetwork): Payment {
            return Payment(payment.order, payment.time, payment.sum, payment.status)
        }
    }
}