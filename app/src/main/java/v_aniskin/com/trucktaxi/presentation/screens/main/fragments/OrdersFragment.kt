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
import v_aniskin.com.trucktaxi.domain.models.ListItemTypes
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtOrdersVC
import java.util.*

/**
 * Created by root on 28.05.17.
 */
class OrdersFragment : BaseParentFragment<FmtOrdersVC>() {

    companion object {
        val ORDERS_FRAGMENT_ID: String = "main.orders_fragment"
        val FRAGMENT_TYPE_BY_ORDER: String = "fragment_type_by_order"
        val FRAGMENT_TYPE_CURRENT: Int = 0
        val FRAGMENT_TYPE_FUTURE: Int = 1
        val FRAGMENT_TYPE_HISTORY: Int = 2
    }

    @BindView(R.id.fmt_list_rv_data)
    lateinit var mRvData: RecyclerView
    @BindView(R.id.fmt_list_tv_error)
    lateinit var mTvError: TextView

    private var mAdapterHeader: MultyHeaderRvAdapter<Order, FmtOrdersVC>? = null
    private var mFragmentTypeByOrder: Int = -1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_list, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mFragmentTypeByOrder = arguments.getInt(FRAGMENT_TYPE_BY_ORDER, -1)
        mViewController = FmtOrdersVC(this)
        mRvData.setLayoutManager(LinearLayoutManager(getContext()))
        val headerBinder = object : SubsidaryBinder<Order>  {
            override fun bind(view: View, data: Order) {
                (view.findViewById(R.id.v_order_header_tv_order_text) as TextView).setText(data.desc)
            }
        }
        mAdapterHeader = MultyHeaderRvAdapter(SubsidaryHolder(context, R.layout.v_order_header, headerBinder), OrderHolder(context, mViewController))
        mRvData.adapter = mAdapterHeader
    }

    override fun onStart() {
        super.onStart()
        mViewController?.start()
    }

    override fun onStop() {
        super.onStop()
        mViewController?.stop()
    }

    fun getFragmentType(): Int = mFragmentTypeByOrder

    fun loadOrders(orders: List<Order>) {
        if (orders.isEmpty())
            mTvError.visibility = View.VISIBLE
        else
            mTvError.visibility = View.INVISIBLE

        val data: ArrayList<AdapterItemContainer<Order>> = ArrayList()
        orders.forEach {
            if (it.state == ListItemTypes.TYPE_HEADER)
                data.add(AdapterItemContainer(MultyHeaderRvAdapter.VIEW_TYPE_HEADER, it))
            else
                data.add(AdapterItemContainer(MultyHeaderRvAdapter.VIEW_TYPE_HOLDER, it))
        }
        mAdapterHeader?.loadData(data)
    }
}