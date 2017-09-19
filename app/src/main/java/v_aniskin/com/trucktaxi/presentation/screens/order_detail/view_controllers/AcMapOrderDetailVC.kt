package v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers

import android.arch.lifecycle.Observer
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.di.components.DaggerMapOrderComponent
import v_aniskin.com.trucktaxi.application.di.modules.MapOrderModule
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.data.location.LocationLiveData
import v_aniskin.com.trucktaxi.domain.executors.interfaces.LocationExecutor
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.MapOrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.utils.RouterController
import javax.inject.Inject

/**
 * Created by root on 31.05.17.
 */
class AcMapOrderDetailVC(view: MapOrderDetailActivity) : BaseViewController<MapOrderDetailActivity>(view), Observer<LatLng> {

    @Inject
    lateinit var mLocationExecutor: LocationExecutor
    @Inject
    lateinit var mOrdersExecutor: OrdersExecutor

    private var mRouteController: RouterController? = null
    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    override fun onChanged(t: LatLng?) {
        mRouteController?.showCurrentPoint(t!!)
    }

    override fun inject() {
        super.inject()
        DaggerMapOrderComponent.builder()
                .screenComponent(mView.getScreenComponet())
                .mapOrderModule(MapOrderModule(mView))
                .build()
                .inject(this)
    }

    override fun stop() {
        super.stop()
        mSubscriptionContainer.unsubscribeAll()
    }

    fun showOrdersDetailScreen(status: String) {
        val intent = Intent(mView, OrderDetailActivity::class.java)
        intent.putExtra(OrderDetailActivity.ORDER_DETAIL_ACTIVITY_ID, status)
        intent.putExtra(OrderDetailActivity.ORDER_ID_KEY, mView.getOrderId())
        mView.startActivity(intent)
    }

    fun loadGoogleMap(map: GoogleMap) {
        mRouteController = RouterController(map, mView)

        mSubscriptionContainer.addSubscription(mOrdersExecutor.getOrderDetail(mView.getOrderId())
                .subscribe({ doOnGetOrderDetail(it) }, { doOnError(it) }))

        startLocationUpdate()
        loadRoute()
    }

    fun startLocationUpdate() {
        mLocationExecutor.startLocationUpdate().subscribe({ doOnGetLocationLiveData(it) }, { doOnError(it) })
    }

    private fun loadRoute() {
        mSubscriptionContainer.addSubscription(mOrdersExecutor
                .getRoutePoints(mView.getOrderId())
                .subscribe({ mRouteController!!.createRote(it) }, { doOnError(it) }))
    }

    private fun doOnGetLocationLiveData(location: LocationLiveData) {
        location.removeObserver(this)
        location.observe(mView, this)
    }

    private fun doOnGetOrderDetail(order: ModelContainer<Order>) {
        val orderId = mView.getString(R.string.order_prefix)+" №"+order.mData!!.orderId
        mView.setTitle(orderId)
        for (i: Int in order.mData!!.orderRoutepoints.indices) {
            val orderPoint = order.mData!!.orderRoutepoints[i]
            val coords = orderPoint.routepointLocation
            val latLng = LatLng(coords[0].toDouble(), coords[1].toDouble())
            if (i == 0)
                mRouteController?.showStartPoint(latLng)
            else
                mRouteController?.showFinishPoint(latLng)
        }
    }

    private fun doOnError(error: Throwable) {
        Toast.makeText(mView, R.string.load_on_error_data, Toast.LENGTH_SHORT).show()
        Logger.logError(error)
    }
}