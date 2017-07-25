package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.mappers.OrdersMapper
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.presentation.models.ModelsContainer
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import javax.inject.Inject

/**
 * Created by root on 25.07.17.
 */
@PerMainScreen
class OrdersExecutorImpl @Inject constructor(var mRepository: Repository) : OrdersExecutor {

    init {
        Logger.testLog("OrdersExecutorImpl Create")
    }

    override fun getOrders(): Observable<ModelsContainer<OrderPresent>> {
        return mRepository.getAllOrders()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {response ->
                    val ordersDomain = OrdersMapper.mapOrderResponse(response)
                    OrdersMapper.mapOrders(ordersDomain, response.error, response.status)}
    }
}