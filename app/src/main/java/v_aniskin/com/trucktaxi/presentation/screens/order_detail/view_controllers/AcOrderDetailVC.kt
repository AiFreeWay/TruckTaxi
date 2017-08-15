package v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers

import v_aniskin.com.trucktaxi.application.di.components.DaggerOrderDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.OrderDetailScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.OrderDetailScreenModule
import v_aniskin.com.trucktaxi.presentation.factories.OrdersPaymentsFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity
import javax.inject.Inject

/**
 * Created by root on 01.06.17.
 */
class AcOrderDetailVC(view: OrderDetailActivity) : BaseViewController<OrderDetailActivity>(view) {

    @Inject
    lateinit var mFactory: OrdersPaymentsFragmentFactory

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
        mView.loadData(mFactory.getAllItems(mView))
    }
}