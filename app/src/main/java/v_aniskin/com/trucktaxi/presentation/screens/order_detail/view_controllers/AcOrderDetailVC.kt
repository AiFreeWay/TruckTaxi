package v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers

import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.di.components.DaggerOrderDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.OrderDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.OrderDetailScreenModule
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.application.utils.SubscriptionContainer
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.factories.OrdersPaymentsFragmentFactory
import v_aniskin.com.trucktaxi.presentation.models.ModelContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity
import javax.inject.Inject

/**
 * Created by root on 01.06.17.
 */
class AcOrderDetailVC(view: OrderDetailActivity) : BaseViewController<OrderDetailActivity>(view) {

    @Inject
    lateinit var mFactory: OrdersPaymentsFragmentFactory
    @Inject
    lateinit var mOrdersExecutor: OrdersExecutor

    private val mSubscriptionContainer: SubscriptionContainer = SubscriptionContainer()

    private var mOrderDetailScreenComponent: OrderDetailScreenComponent? = null

    override fun inject() {
        mOrderDetailScreenComponent = DaggerOrderDetailScreenComponent.builder()
                .screenComponent(mView.getScreenComponet())
                .orderDetailScreenModule(OrderDetailScreenModule(mView))
                .build()
        mOrderDetailScreenComponent?.inject(this)
    }

    override fun start() {
        super.start()
        getOrderDetail()
    }

    override fun stop() {
        super.stop()
        mSubscriptionContainer.unsubscribeAll()
    }

    fun getOrderDetail() {
        mSubscriptionContainer.addSubscription(mOrdersExecutor
                .getOrderDetail(mView.getOrderId())
                .doOnSubscribe { mView.startProgress() }
                .subscribe({ doOnGetOrderDetail(it) }, { doOnError(it) }))
    }

    fun doOnGetOrderDetail(order: ModelContainer<Order>) {
        mView.loadData(mFactory.getAllItems(mView.getOrderId(), mView))
        mView.loadOrder(order.mData!!)
    }

    private fun doOnError(error: Throwable) {
        mView.stopProgress()
        mView.showToast(R.string.load_on_error_data)
        Logger.logError(error)
    }
}