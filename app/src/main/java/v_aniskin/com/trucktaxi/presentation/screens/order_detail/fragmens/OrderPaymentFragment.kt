package v_aniskin.com.trucktaxi.presentation.screens.order_detail.fragmens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers.FmtOrderPaymentVC

/**
 * Created by root on 01.06.17.
 */
class OrderPaymentFragment : BaseParentFragment<FmtOrderPaymentVC>() {

    companion object {
        val ORDER_PAYMENT_RAGMENT_ID: String = "orderdetail.payment_fragment"
        val PAYMENT_TYPE: String = "payment_type"
        val PAYMENT_ID: String = "payment_id"
    }

    @BindView(R.id.fmt_order_payment_tv_all_hours)
    lateinit var mTvAllHours: TextView
    @BindView(R.id.fmt_order_payment_tv_worked_hours)
    lateinit var mTvWorkedHours: TextView
    @BindView(R.id.fmt_order_payment_tv_support_hours)
    lateinit var mTvSupportHours: TextView

    @BindView(R.id.fmt_order_payment_tv_km_cost)
    lateinit var mTvKmCost: TextView

    @BindView(R.id.fmt_order_payment_tv_entry)
    lateinit var mTvEntry: TextView
    @BindView(R.id.fmt_order_payment_tv_loading_hours)
    lateinit var mTvLoadingHours: TextView
    @BindView(R.id.fmt_order_payment_tv_rastentovka)
    lateinit var mTvRastentovka: TextView
    @BindView(R.id.fmt_order_payment_tv_total)
    lateinit var mTvTotal: TextView

    private lateinit var mPaymentType: String
    private lateinit var mPaymentId: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_order_payment, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtOrderPaymentVC(this)
        mPaymentType = arguments.getString(PAYMENT_TYPE)
        mPaymentId = arguments.getString(PAYMENT_ID)
    }

    override fun onStart() {
        super.onStart()
        mViewController?.start()
    }

    override fun onStop() {
        super.onStop()
        mViewController?.stop()
    }

    fun startProgress() {
        (activity as OrderDetailActivity).startProgress()
    }

    fun stopProgress() {
        (activity as OrderDetailActivity).stopProgress()
    }

    fun showToast(text: Int) {
        (activity as OrderDetailActivity).showToast(text)
    }

    fun getPaymentId() = mPaymentId

    fun getPaymentType() = mPaymentType

    fun loadPayment(payment: Payment) {
        mTvAllHours.setText(payment.allHours)
        mTvWorkedHours.setText(payment.workedHours)
        mTvSupportHours.setText(payment.supportHours)

        mTvKmCost.setText(payment.kmCost)

        mTvEntry.setText(payment.entry)
        mTvLoadingHours.setText(payment.loadingHours)
        mTvRastentovka.setText(payment.rastentovka)
        mTvTotal.setText(payment.total)
    }
}