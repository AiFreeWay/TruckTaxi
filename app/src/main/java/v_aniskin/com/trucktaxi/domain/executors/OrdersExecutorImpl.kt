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

    override fun getFeatureOrders(): Observable<ModelsContainer<OrderPresent>> {
        return mRepository.getPreappointedOrders()
                .zipWith(mRepository.getAppointedOrders(), { r1, r2 ->
                    val orders: ModelsContainer<Order> = ModelsContainer(r1.error, r1.status)
                    if (r1.orders.isNotEmpty())
                        orders.mData.add(Order("Предварительное назначение", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r1.orders)
                    if (r2.orders.isNotEmpty())
                        orders.mData.add(Order("Утвержденные", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r2.orders)
                    orders })
                .zipWith(mRepository.getNewOrders(), { orders, r2 ->
                    if (r2.orders.isNotEmpty())
                        orders.mData.add(Order("Новые", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r2.orders)
                    orders })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {OrdersMapper.mapOrders(it)}
    }

    override fun getCurrentOrders(): Observable<ModelsContainer<OrderPresent>> {
        return mRepository.getCurrentOrders()
                .map {
                    val orders: ModelsContainer<Order> = ModelsContainer(it.error, it.status)
                    orders.mData.addAll(it.orders)
                    OrdersMapper.mapOrders(orders)
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getHistoryOrders(): Observable<ModelsContainer<OrderPresent>> {
        return mRepository.getCompleteOrders()
                .zipWith(mRepository.getArchiveOrders(), { r1, r2 ->
                    val orders: ModelsContainer<Order> = ModelsContainer(r1.error, r1.status)
                    if (r1.orders.isNotEmpty())
                        orders.mData.add(Order("Завершённые", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r1.orders)
                    if (r2.orders.isNotEmpty())
                        orders.mData.add(Order("Архив", OrderPresent.STATE_HEADER))
                    orders.mData.addAll(r2.orders)
                    orders })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {OrdersMapper.mapOrders(it)}
    }
}