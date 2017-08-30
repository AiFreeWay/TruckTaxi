package v_aniskin.com.trucktaxi.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtOrdersVC

/**
 * Created by root on 28.05.17.
 */
class OrderHolder: BaseHolder<Order, FmtOrdersVC> {

    @BindView(R.id.v_order_holder_tv_order)
    lateinit var mTvOrder: TextView
    @BindView(R.id.v_order_holder_tv_time)
    lateinit var mTvTime: TextView
    @BindView(R.id.v_order_holder_tv_address)
    lateinit var mTvAddress: TextView
    @BindView(R.id.v_order_holder_tv_work_time_and_payment)
    lateinit var mTvWorkTimeAndPayment: TextView

    constructor(context: Context, viewController: FmtOrdersVC?) : super(context, viewController) {}

    constructor(view: View, viewController: FmtOrdersVC?) : super(view, viewController) {
        ButterKnife.bind(this, mView)
    }

    override fun create(viewGroup: ViewGroup): BaseHolder<Order, FmtOrdersVC> {
        val view = viewInflater(viewGroup, R.layout.v_order_holder)
        return OrderHolder(view, mViewController)
    }

    override fun bind(dataModel: Order) {
        val orderId = itemView.context.getString(R.string.order_prefix)+" "+dataModel.orderId
        val orderTime = itemView.context.getString(R.string.on)+" "+dataModel.orderTimeStart
        val addres = dataModel.startRoutePointAddress+" - "+dataModel.finalRoutePointAddress
        val desc = dataModel.orderWorkTime+" "+itemView.context.getString(R.string.hour_suffix)+" - "+dataModel.orderPrice+" "+itemView.context.getString(R.string.ruble)
        mTvOrder.setText(orderId)
        mTvTime.setText(orderTime)
        mTvAddress.setText(addres)
        mTvWorkTimeAndPayment.setText(desc)
        mView.setOnClickListener {
            mViewController?.showOrderDetailScreen(dataModel.orderId, dataModel.orderStatus)
        }
    }
}