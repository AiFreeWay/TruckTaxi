package v_aniskin.com.trucktaxi.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.models.PaymentPresent
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtOrdersVC
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtPaymentsVC

/**
 * Created by root on 28.05.17.
 */
class PaymentHolder : BaseHolder<PaymentPresent, FmtPaymentsVC> {

    @BindView(R.id.v_payment_holder_tv_order)
    lateinit var mTvOrder: TextView
    @BindView(R.id.v_payment_holder_tv_time)
    lateinit var mTvTime: TextView
    @BindView(R.id.v_payment_holder_tv_sum)
    lateinit var mTvSum: TextView
    @BindView(R.id.v_payment_holder_tv_state)
    lateinit var mTvState: TextView

    constructor(context: Context, viewController: FmtPaymentsVC?) : super(context, viewController) {}

    constructor(view: View, viewController: FmtPaymentsVC?) : super(view, viewController) {
        ButterKnife.bind(this, mView)
    }

    override fun create(viewGroup: ViewGroup): BaseHolder<PaymentPresent, FmtPaymentsVC> {
        val view = viewInflater(viewGroup, R.layout.v_payment_holder)
        return PaymentHolder(view, mViewController)
    }

    override fun bind(dataModel: PaymentPresent) {
        mTvOrder.setText(dataModel.order)
        mTvTime.setText(dataModel.time)
        mTvSum.setText(dataModel.sum)
        mTvState.setText(dataModel.status)
        mView.setOnClickListener { mViewController?.showPaymentDetailScreen() }
    }
}