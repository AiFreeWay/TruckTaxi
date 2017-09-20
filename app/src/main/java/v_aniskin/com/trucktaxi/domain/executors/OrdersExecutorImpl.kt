package v_aniskin.com.trucktaxi.domain.executors

import com.google.android.gms.maps.model.LatLng
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.network_client.responses.LocationResponse
import v_aniskin.com.trucktaxi.data.network_client.responses.OrdersResponse
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.mappers.OrdersMapper
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.domain.repositories.Repository
import v_aniskin.com.trucktaxi.domain.models.ListItemTypes
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import java.util.*
import javax.inject.Inject

/**
 * Created by root on 25.07.17.
 */
class OrdersExecutorImpl @Inject constructor(var mRepository: Repository) : OrdersExecutor {

    init {
        Logger.testLog("OrdersExecutorImpl Create")
    }

    override fun getFeatureOrders(): Observable<ModelContainer<List<Order>>> {
        return mRepository.getPreappointedOrders()
                .zipWith(mRepository.getAppointedOrders(), { r1, r2 -> zipPreappointedAndAppointedOrders(r1, r2)})
                .zipWith(mRepository.getNewOrders(), { orders, r2 -> zipZippedAndNewOrders(orders, r2) })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCurrentOrders(): Observable<ModelContainer<List<Order>>> {
        return mRepository.getCurrentOrders()
                .map { orders -> mapCurrentOrders(orders) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getHistoryOrders(): Observable<ModelContainer<List<Order>>> {
        return mRepository.getCompleteOrders()
                .zipWith(mRepository.getArchiveOrders(), { r1, r2 -> zipCompleteAndArchiveOrders(r1, r2) })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getOrderDetail(orderId: String): Observable<ModelContainer<Order>> {
        return mRepository.getOrder(orderId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { ModelContainer(OrdersMapper.mapOrder(it.orderData), it.error, it.status) }
    }

    override fun getRoutePoints(orderId: String): Observable<List<LatLng>> {
        return mRepository.getRoutePoints(orderId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { mapRoute(it) }
    }

    private fun zipPreappointedAndAppointedOrders(preappointedOrders: OrdersResponse, appointedOrders: OrdersResponse): ModelContainer<List<Order>> {
        val ordersContainer: ModelContainer<List<Order>> = ModelContainer(preappointedOrders.error, preappointedOrders.status)
        val orderItems = ArrayList<Order>()
        if (preappointedOrders.ordersList.isNotEmpty())
            orderItems.add(Order("Предварительное назначение", ListItemTypes.TYPE_HEADER))
        orderItems.addAll(OrdersMapper.mapOrdersNetwork(preappointedOrders.ordersList))

        if (appointedOrders.ordersList.isNotEmpty())
            orderItems.add(Order("Утвержденные", ListItemTypes.TYPE_HEADER))
        orderItems.addAll(OrdersMapper.mapOrdersNetwork(appointedOrders.ordersList))
        ordersContainer.mData = orderItems
        return ordersContainer
    }

    private fun zipZippedAndNewOrders(zippedOrders: ModelContainer<List<Order>>, newOrders: OrdersResponse): ModelContainer<List<Order>> {
        val orderItems = ArrayList(zippedOrders.mData)
        if (newOrders.ordersList.isNotEmpty())
            orderItems.add(Order("Новые", ListItemTypes.TYPE_HEADER))
        orderItems.addAll(OrdersMapper.mapOrdersNetwork(newOrders.ordersList))
        zippedOrders.mData = orderItems
        return zippedOrders
    }

    private fun mapCurrentOrders(currentOrders: OrdersResponse): ModelContainer<List<Order>> {
        val ordersContainer: ModelContainer<List<Order>> = ModelContainer(currentOrders.error, currentOrders.status)
        val orderItems = ArrayList<Order>()
        orderItems.addAll(OrdersMapper.mapOrdersNetwork(currentOrders.ordersList))
        ordersContainer.mData = orderItems
        return ordersContainer
    }

    private fun zipCompleteAndArchiveOrders(completeOrders: OrdersResponse, archiveOrders: OrdersResponse): ModelContainer<List<Order>> {
        val ordersContainer: ModelContainer<List<Order>> = ModelContainer(completeOrders.error, completeOrders.status)
        val orderItems = ArrayList<Order>()
        if (completeOrders.ordersList.isNotEmpty())
            orderItems.add(Order("Завершённые", ListItemTypes.TYPE_HEADER))
        orderItems.addAll(OrdersMapper.mapOrdersNetwork(completeOrders.ordersList))

        if (archiveOrders.ordersList.isNotEmpty())
            orderItems.add(Order("Архив", ListItemTypes.TYPE_HEADER))
        orderItems.addAll(OrdersMapper.mapOrdersNetwork(archiveOrders.ordersList))
        ordersContainer.mData = orderItems
        return ordersContainer
    }

    private fun mapRoute(response: LocationResponse): List<LatLng> {
        val mappedList = ArrayList<LatLng>()
        response.georouteData.georouteGeoetry.forEach {
            mappedList.add(LatLng(it.get(0), it.get(1)))
        }
        return mappedList
    }
}