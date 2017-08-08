package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.PaymentsExecutor
import v_aniskin.com.trucktaxi.domain.mappers.PaymentsMapper
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
        return mRepository.getPayments()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {response -> PaymentsMapper.mapPayments(response)}
    }
}