package v_aniskin.com.trucktaxi.domain.executors

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.di.scopes.PerMainScreen
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.responses.OrdersResponse
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.mappers.OrdersMapper
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.domain.models.ListItemTypes
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
                .zipWith(mRepository.getAppointedOrders(), { r1, r2 -> zipPreappointedAndAppointedOrders(r1, r2)})
                .zipWith(mRepository.getNewOrders(), { orders, r2 -> zipZippedAndNewOrders(orders, r2) })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {OrdersMapper.mapOrders(it)}
    }

    override fun getCurrentOrders(): Observable<ModelsContainer<OrderPresent>> {
        return mRepository.getCurrentOrders()
                .map { orders -> mapCurrentOrders(orders) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getHistoryOrders(): Observable<ModelsContainer<OrderPresent>> {
        return mRepository.getCompleteOrders()
                .zipWith(mRepository.getArchiveOrders(), { r1, r2 -> zipCompleteAndArchiveOrders(r1, r2) })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {OrdersMapper.mapOrders(it)}
    }

    private fun zipPreappointedAndAppointedOrders(preappointedOrders: OrdersResponse, appointedOrders: OrdersResponse): ModelsContainer<Order> {
        val orders: ModelsContainer<Order> = ModelsContainer(preappointedOrders.error, preappointedOrders.status)
        if (preappointedOrders.orders.isNotEmpty())
            orders.mData.add(Order("Предварительное назначение", ListItemTypes.TYPE_HEADER))
        orders.mData.addAll(OrdersMapper.mapOrdersNetwork(preappointedOrders.orders))

        if (appointedOrders.orders.isNotEmpty())
            orders.mData.add(Order("Утвержденные", ListItemTypes.TYPE_HEADER))
        orders.mData.addAll(OrdersMapper.mapOrdersNetwork(appointedOrders.orders))
        return orders
    }

    private fun zipZippedAndNewOrders(zippedOrders: ModelsContainer<Order>, newOrders: OrdersResponse): ModelsContainer<Order> {
        if (newOrders.orders.isNotEmpty())
            zippedOrders.mData.add(Order("Новые", ListItemTypes.TYPE_HEADER))
        zippedOrders.mData.addAll(OrdersMapper.mapOrdersNetwork(newOrders.orders))
        return zippedOrders
    }

    private fun mapCurrentOrders(currentOrders: OrdersResponse): ModelsContainer<OrderPresent> {
        val orders: ModelsContainer<Order> = ModelsContainer(currentOrders.error, currentOrders.status)
        orders.mData.addAll(OrdersMapper.mapOrdersNetwork(currentOrders.orders))
        return OrdersMapper.mapOrders(orders)
    }

    private fun zipCompleteAndArchiveOrders(completeOrders: OrdersResponse, archiveOrders: OrdersResponse): ModelsContainer<Order> {
        val orders: ModelsContainer<Order> = ModelsContainer(completeOrders.error, completeOrders.status)
        if (completeOrders.orders.isNotEmpty())
            orders.mData.add(Order("Завершённые", ListItemTypes.TYPE_HEADER))
        orders.mData.addAll(OrdersMapper.mapOrdersNetwork(completeOrders.orders))

        if (archiveOrders.orders.isNotEmpty())
            orders.mData.add(Order("Архив", ListItemTypes.TYPE_HEADER))
        orders.mData.addAll(OrdersMapper.mapOrdersNetwork(archiveOrders.orders))
        return orders
    }
}