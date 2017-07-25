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
import v_aniskin.com.trucktaxi.presentation.adapters.holders.OrderHolder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryBinder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryHolder
import v_aniskin.com.trucktaxi.presentation.models.AdapterItemContainer
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtOrdersVC
import java.util.*

/**
 * Created by root on 28.05.17.
 */
class OrdersFragment : BaseParentFragment<FmtOrdersVC>() {

    companion object {
        val ORDERS_FRAGMENT_ID: String = "main.orders_fragment"
    }

    @BindView(R.id.fmt_list_rv_data)
    lateinit var mRvData: RecyclerView

    private var mAdapterHeader: MultyHeaderRvAdapter<OrderPresent, FmtOrdersVC>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_list, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtOrdersVC(this)
        mRvData.setLayoutManager(LinearLayoutManager(getContext()))
        val headerBinder = object : SubsidaryBinder<OrderPresent>  {
            override fun bind(view: View, data: OrderPresent) {
                (view.findViewById(R.id.v_order_header_tv_order_text) as TextView).setText(data.desc)
            }
        }
        mAdapterHeader = MultyHeaderRvAdapter(SubsidaryHolder(context, R.layout.v_order_header, headerBinder), OrderHolder(context, mViewController))
        mRvData.adapter = mAdapterHeader
    }

    override fun onResume() {
        super.onResume()
        getToolbar().setTitle(getString(R.string.orders))
    }

    fun loadOrders(orders: List<OrderPresent>) {
        val data: ArrayList<AdapterItemContainer<OrderPresent>> = ArrayList()
        orders.forEach {
            if (it.state == OrderPresent.STATE_HEADER)
                data.add(AdapterItemContainer(MultyHeaderRvAdapter.VIEW_TYPE_HEADER, it))
            else
                data.add(AdapterItemContainer(MultyHeaderRvAdapter.VIEW_TYPE_HOLDER, it))
        }
        mAdapterHeader?.loadData(data)
    }
}