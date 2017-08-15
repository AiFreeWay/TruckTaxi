package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.responses.PaymentsResponse
import v_aniskin.com.trucktaxi.domain.executors.interfaces.PaymentsExecutor
import v_aniskin.com.trucktaxi.domain.mappers.PaymentsMapper
import v_aniskin.com.trucktaxi.domain.models.ListItemTypes
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.PaymentPresent
import javax.inject.Inject

/**
 * Created by root on 08.08.17.
 */
@PerMainScreen
class PaymentsExecutorImpl @Inject constructor(var mRepository: Repository) : PaymentsExecutor {

    init {
        Logger.testLog("OrdersExecutorImpl Create")
    }

    override fun getPayments(): Observable<ModelsContainer<PaymentPresent>> {
        return mRepository.getPaymentsFuture()
                .zipWith(mRepository.getPaymentsComlete(), { r1, r2 -> zipPayments(r1, r2) })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {PaymentsMapper.mapPayments(it)}
    }

    private fun zipPayments(futurePayments: PaymentsResponse, completePayments: PaymentsResponse): ModelsContainer<Payment> {
        val payments: ModelsContainer<Payment> = ModelsContainer(completePayments.error, completePayments.status)
        if (futurePayments.payments.isNotEmpty())
            payments.mData.add(Payment("Предстоящие", "", ListItemTypes.TYPE_HEADER))
        payments.mData.addAll(PaymentsMapper.mapPaymentsNetwork(futurePayments.payments))

        if (completePayments.payments.isNotEmpty())
            payments.mData.add(Payment("Завершённые", "", ListItemTypes.TYPE_HEADER))
        payments.mData.addAll(PaymentsMapper.mapPaymentsNetwork(completePayments.payments))
        return payments
    }
}