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
import v_aniskin.com.trucktaxi.presentation.adapters.holders.PaymentHolder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryBinder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryHolder
import v_aniskin.com.trucktaxi.presentation.models.AdapterItemContainer
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

    private var mAdapter: MultyRvAdapter<PaymentPresent, FmtPaymentsVC>? = null

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
        mAdapter = MultyRvAdapter(SubsidaryHolder(context, R.layout.v_payment_header, headerBinder), PaymentHolder(context, mViewController))
        mRvData.adapter = mAdapter
        test()
    }

    override fun onResume() {
        super.onResume()
        getToolbar().setTitle(getString(R.string.payments))
    }

    fun test() {
        val data: ArrayList<AdapterItemContainer<PaymentPresent>> = ArrayList()
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HEADER, PaymentPresent("Предстоящие", "Итого к выплате: 2.000 руб.")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, PaymentPresent("Заказ №0", "на 22.05.2017 в 12:00", "К выплате: 2.000 руб.", "Статус: в работе")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HEADER, PaymentPresent("Совершенные", "Итого к выплачено: 10.000 руб.")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, PaymentPresent("Заказ №4", "на 22.05.2017 в 14:00", "К выплате: 2.500 руб.", "Статус: выполнено 21.05.2017")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, PaymentPresent("Заказ №5", "на 22.05.2017 в 16:00", "К выплате: 4.500 руб.", "Статус: выполнено 21.05.2017")))
        data.add(AdapterItemContainer(MultyRvAdapter.VIEW_TYPE_HOLDER, PaymentPresent("Заказ №6", "на 22.05.2017 в 18:00", "К выплате: 4.000 руб.", "Статус: выполнено 21.05.2017")))
        mAdapter?.loadData(data)
    }
}
