package v_aniskin.com.trucktaxi.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtOrdersVC

/**
 * Created by root on 28.05.17.
 */
class OrderHolder: BaseHolder<OrderPresent, FmtOrdersVC> {

    @BindView(R.id.v_order_holder_tv_order)
    lateinit var mTvOrder: TextView
    @BindView(R.id.v_order_holder_tv_time)
    lateinit var mTvTime: TextView
    @BindView(R.id.v_order_holder_tv_address)
    lateinit var mTvAddress: TextView
    @BindView(R.id.v_order_holder_tv_work_time)
    lateinit var mTvWorkTime: TextView

    constructor(context: Context, viewController: FmtOrdersVC?) : super(context, viewController) {}

    constructor(view: View, viewController: FmtOrdersVC?) : super(view, viewController) {
        ButterKnife.bind(this, mView)
    }

    override fun create(viewGroup: ViewGroup): BaseHolder<OrderPresent, FmtOrdersVC> {
        val view = viewInflater(viewGroup, R.layout.v_order_holder)
        return OrderHolder(view, mViewController)
    }

    override fun bind(dataModel: OrderPresent) {
        mTvOrder.setText(dataModel.order)
        mTvTime.setText(dataModel.time)
        mTvAddress.setText(dataModel.address)
        mTvWorkTime.setText(dataModel.workTime)
        mView.setOnClickListener {
            mViewController?.showOrderDetailScreen(dataModel?.status)
        }
    }
}