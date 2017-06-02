package v_aniskin.com.trucktaxi.presentation.screens.order_detail.fragmens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment

/**
 * Created by root on 01.06.17.
 */
class OrderPaymentFragment : BaseParentFragment<Any>() {

    companion object {
        val ORDER_PAYMENT_RAGMENT_ID: String = "orderdetail.payment_fragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_order_payment, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}