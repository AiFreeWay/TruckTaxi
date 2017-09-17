package v_aniskin.com.trucktaxi.presentation.screens.payment_detail.fragments

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.domain.models.Payment
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.activities.PaymentDetailActivity
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.view_controllers.FmtPaymentVC

/**
 * Created by root on 01.06.17.
 */
class PaymentFragment : BaseParentFragment<FmtPaymentVC>() {

    companion object {
        val PAYMENT_RAGMENT_ID: String = "paymentdetail.payment_fragment"
        val PAYMENT_TYPE: String = "payment_type"
        val PAYMENT_ID: String = "payment_id"
    }

    @BindView(R.id.fmt_payment_tv_begin_work)
    lateinit var mTvBeginWork: TextView
    @BindView(R.id.fmt_payment_tv_end_works)
    lateinit var mTvEndWork: TextView
    @BindView(R.id.fmt_payment_tv_all_worked_hours)
    lateinit var mTvAllWorkedHours: TextView

    @BindView(R.id.fmt_payment_tv_count)
    lateinit var mTvCount: TextView
    @BindView(R.id.fmt_payment_tv_minimal_tarif)
    lateinit var mTvMinimalTarif: TextView
    @BindView(R.id.fmt_payment_tv_hours_cost)
    lateinit var mTvHoursCost: TextView
    @BindView(R.id.fmt_payment_tv_mkad_pass_title)
    lateinit var mTvMkadPassTitle: TextView
    @BindView(R.id.fmt_payment_tv_ttk_pass_title)
    lateinit var mTvTtkPassTitle: TextView
    @BindView(R.id.fmt_payment_tv_mkad_pass)
    lateinit var mTvMkadPass: TextView
    @BindView(R.id.fmt_payment_tv_ttk_pass)
    lateinit var mTvTtkPass: TextView
    @BindView(R.id.fmt_payment_tv_sum)
    lateinit var mTvSum: TextView


    @BindView(R.id.fmt_payment_tv_km_count_value)
    lateinit var mTvKmCountValue: TextView
    @BindView(R.id.fmt_payment_tv_km_cost_mkad_hours)
    lateinit var mTvCostMkadHours: TextView
    @BindView(R.id.fmt_payment_tv_km_sum)
    lateinit var mTvKmSum: TextView


    @BindView(R.id.fmt_payment_tv_total_on_tarif)
    lateinit var mTvTotalOnTarif: TextView
    @BindView(R.id.fmt_payment_tv_fines_title)
    lateinit var mTvFinesTitle: TextView
    @BindView(R.id.fmt_payment_tv_fines)
    lateinit var mTvFines: TextView
    @BindView(R.id.fmt_payment_tv_payed_customer_title)
    lateinit var mTvPayedCustomerTitle: TextView
    @BindView(R.id.fmt_payment_tv_payed_customer)
    lateinit var mTvPayedCustomer: TextView
    @BindView(R.id.fmt_payment_tv_commissions)
    lateinit var mTvPayedCommissions: TextView
    @BindView(R.id.fmt_payment_tv_total_pay)
    lateinit var mTvPayedTotalPay: TextView

    private lateinit var mPaymentType: String
    private lateinit var mPaymentId: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_payment, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtPaymentVC(this)
        mPaymentType = arguments.getString(PAYMENT_TYPE)
        mPaymentId = arguments.getString(PAYMENT_ID)
    }

    fun startProgress() {
        (activity as PaymentDetailActivity).startProgress()
    }

    fun stopProgress() {
        (activity as PaymentDetailActivity).stopProgress()
    }

    fun showToast(text: Int) {
        (activity as PaymentDetailActivity).showToast(text)
    }

    fun getPaymentId() = mPaymentId

    fun getPaymentType() = mPaymentType

    fun loadPayment(payment: Payment) {
        mTvBeginWork.setText(payment.beginWork)
        mTvEndWork.setText(payment.endWork)
        mTvAllWorkedHours.setText(payment.allWorkedHours)

        mTvCount.setText(payment.count)
        mTvMinimalTarif.setText(payment.minimalTarif)
        mTvHoursCost.setText(payment.hoursCost)

        if (payment.mkadPass != null) {
            mTvMkadPass.setText(payment.mkadPass)
            mTvMkadPassTitle.visibility = View.VISIBLE
            mTvMkadPass.visibility = View.VISIBLE
        } else {
            mTvMkadPassTitle.visibility = View.GONE
            mTvMkadPass.visibility = View.GONE
        }

        if (payment.ttkPass != null) {
            mTvTtkPass.setText(payment.ttkPass)
            mTvTtkPassTitle.visibility = View.VISIBLE
            mTvTtkPass.visibility = View.VISIBLE
        } else {
            mTvTtkPassTitle.visibility = View.GONE
            mTvTtkPass.visibility = View.GONE
        }

        mTvSum.setText(payment.sum)

        mTvKmCountValue.setText(payment.kmCountValue)
        mTvCostMkadHours.setText(payment.costMkadHours)
        mTvKmSum.setText(payment.kmSum)

        mTvTotalOnTarif.setText(payment.totalOnTarif)

        if (payment.fines != null) {
            mTvFines.setText(payment.fines)
            mTvFinesTitle.visibility = View.VISIBLE
            mTvFines.visibility = View.VISIBLE
        } else {
            mTvFinesTitle.visibility = View.GONE
            mTvFines.visibility = View.GONE
        }

        if (payment.payedCustomer != null) {
            mTvPayedCustomer.setText(payment.payedCustomer)
            mTvPayedCustomerTitle.visibility = View.VISIBLE
            mTvPayedCustomer.visibility = View.VISIBLE
        } else {
            mTvPayedCustomerTitle.visibility = View.GONE
            mTvPayedCustomer.visibility = View.GONE
        }

        mTvPayedCommissions.setText(payment.payedCommissions)
        mTvPayedTotalPay.setText(payment.payedTotalPay)
    }
}