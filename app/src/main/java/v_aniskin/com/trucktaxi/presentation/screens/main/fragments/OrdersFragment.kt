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
import v_aniskin.com.trucktaxi.presentation.adapters.MultyRvAdapter
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

    private var mAdapter: MultyRvAdapter<OrderPresent, FmtOrdersVC>? = null

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
        mAdapter = MultyRvAdapter(SubsidaryHolder(context, R.layout.v_order_header, headerBinder), OrderHolder(context, mViewController))
        mRvData.adapter = mAdapter
        test()
    }

    fun test() {
        val data: ArrayList<AdapterItemContainer<OrderPresent>> = ArrayList()
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HEADER, OrderPresent("Текущие")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, OrderPresent("Заказ №0", "на 22.05.2017 в 12:00", "ул. Профсоюзная - ул. Моховая", "c 14:00 до 15:00")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HEADER, OrderPresent("Предстоящие")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, OrderPresent("Заказ №1", "на 22.05.2017 в 14:00", "ул. Профсоюзная - ул. Моховая", "c 14:00 до 15:00")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, OrderPresent("Заказ №2", "на 22.05.2017 в 16:00", "ул. Комсомольская - ул. Набережная", "c 16:00 до 17:00")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, OrderPresent("Заказ №3", "на 22.05.2017 в 18:00", "ул. Демократическая - ул. Новоселова", "c 18:00 до 20:00")))
        mAdapter?.loadData(data)
    }
}