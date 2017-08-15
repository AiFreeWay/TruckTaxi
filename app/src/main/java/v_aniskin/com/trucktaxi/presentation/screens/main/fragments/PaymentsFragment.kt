package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.MultyHeaderRvAdapter
import v_aniskin.com.trucktaxi.presentation.adapters.holders.PaymentHolder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryBinder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryHolder
import v_aniskin.com.trucktaxi.presentation.models.AdapterItemContainer
import v_aniskin.com.trucktaxi.domain.models.ListItemTypes
import v_aniskin.com.trucktaxi.presentation.models.PaymentPresent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtPaymentsVC
import java.util.*

/**
 * Created by root on 28.05.17.
 */
class PaymentsFragment : BaseParentFragment<FmtPaymentsVC>() {

    companion object {
        val PAYMENTS_FRAGMENT_ID: String = "main.payments_fragment"
    }

    @BindView(R.id.fmt_list_rv_data)
    lateinit var mRvData: RecyclerView
    @BindView(R.id.fmt_list_tv_error)
    lateinit var mTvError: TextView

    private var mAdapterHeader: MultyHeaderRvAdapter<PaymentPresent, FmtPaymentsVC>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_list, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtPaymentsVC(this)
        mRvData.setLayoutManager(LinearLayoutManager(getContext()))
        val headerBinder = object : SubsidaryBinder<PaymentPresent> {
            override fun bind(view: View, data: PaymentPresent) {
                (view.findViewById(R.id.v_payment_header_tv_order_text) as TextView).setText(data.desc)
                (view.findViewById(R.id.v_payment_header_tv_order_sum) as TextView).setText(data.desc2)
            }
        }
        mAdapterHeader = MultyHeaderRvAdapter(SubsidaryHolder(context, R.layout.v_payment_header, headerBinder), PaymentHolder(context, mViewController))
        mRvData.adapter = mAdapterHeader
    }

    override fun onResume() {
        super.onResume()
        getToolbar().setTitle(getString(R.string.payments))
    }

    override fun onStart() {
        super.onStart()
        mViewController?.start()
    }

    override fun onStop() {
        super.onStop()
        mViewController?.stop()
    }

    fun loadPayments(payments: ArrayList<PaymentPresent>) {
        if (payments.isEmpty())
            mTvError.visibility = View.VISIBLE
        else
            mTvError.visibility = View.INVISIBLE

        val data: ArrayList<AdapterItemContainer<PaymentPresent>> = ArrayList()
        payments.forEach {
            if (it.state == ListItemTypes.TYPE_HEADER)
                data.add(AdapterItemContainer(MultyHeaderRvAdapter.VIEW_TYPE_HEADER, it))
            else
                data.add(AdapterItemContainer(MultyHeaderRvAdapter.VIEW_TYPE_HOLDER, it))
        }
        mAdapterHeader?.loadData(data)
    }
}
