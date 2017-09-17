package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.responses.PaymentsResponse
import v_aniskin.com.trucktaxi.domain.executors.interfaces.PaymentsExecutor
import v_aniskin.com.trucktaxi.domain.mappers.PaymentsMapper
import v_aniskin.com.trucktaxi.domain.models.ListItemTypes
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import java.util.*
import javax.inject.Inject

/**
 * Created by root on 08.08.17.
 */
class PaymentsExecutorImpl @Inject constructor(var mRepository: Repository) : PaymentsExecutor {

    init {
        Logger.testLog("OrdersExecutorImpl Create")
    }

    override fun getPayments(): Observable<ModelContainer<List<Payment>>> {
        return mRepository.getPaymentsFuture()
                .zipWith(mRepository.getPaymentsComlete(), { r1, r2 -> zipPayments(r1, r2) })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPaymentDetail(paymentId: String, paymentType: String): Observable<ModelContainer<Payment>> {
        return mRepository.getPayment(paymentId, paymentType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { ModelContainer(PaymentsMapper.mapPaymentNetwork(it.paymentData), it.error, it.status) }
    }

    private fun zipPayments(futurePayments: PaymentsResponse, completePayments: PaymentsResponse): ModelContainer<List<Payment>> {
        val paymentsContainer: ModelContainer<List<Payment>> = ModelContainer(completePayments.error, completePayments.status)
        val paymentItems = ArrayList<Payment>()
        if (futurePayments.payments.isNotEmpty())
            paymentItems.add(Payment("Предстоящие", "", ListItemTypes.TYPE_HEADER))
        paymentItems.addAll(PaymentsMapper.mapPaymentsNetwork(futurePayments.payments))

        if (completePayments.payments.isNotEmpty())
            paymentItems.add(Payment("Завершённые", "", ListItemTypes.TYPE_HEADER))
        paymentItems.addAll(PaymentsMapper.mapPaymentsNetwork(completePayments.payments))
        paymentsContainer.mData = paymentItems
        return paymentsContainer
    }
}