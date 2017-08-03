package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.mappers.OrdersMapper
import v_aniskin.com.trucktaxi.domain.models.Order
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
        return mRepository.getCurrentOrders()
                .zipWith(mRepository.getNewOrders(), {r1, r2 ->
                    val orders: ModelsContainer<Order> = ModelsContainer(r1.error, r1.status)
                    orders.mData.add(Order("Текущие", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r1.orders)
                    orders.mData.add(Order("Предстоящие", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r2.orders)
                    orders })
                .zipWith(mRepository.getHistoryOrders(), {orders, r2 ->
                    orders.mData.add(Order("История", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r2.orders)
                    orders })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {OrdersMapper.mapOrders(it)}
    }
}