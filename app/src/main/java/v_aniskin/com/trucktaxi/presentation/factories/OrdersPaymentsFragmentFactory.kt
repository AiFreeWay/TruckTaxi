package v_aniskin.com.trucktaxi.presentation.factories

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.PaymentsType
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.fragmens.OrderPaymentFragment
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.fragments.PaymentFragment
import java.util.*

/**
 * Created by root on 01.06.17.
 */
class OrdersPaymentsFragmentFactory {

    fun getAllItems(orderId: String, context: Context): List<ViewPagerItemContainer> {
        val list: ArrayList<ViewPagerItemContainer> = ArrayList<ViewPagerItemContainer>()
        list.add(ViewPagerItemContainer(context.getString(R.string.requistion), addParams(orderId, PaymentsType.ORDER_STATUS_FUTURE, OrderPaymentFragment())))
        list.add(ViewPagerItemContainer(context.getString(R.string.fact), addParams(orderId, PaymentsType.ORDER_STATUS_COMPLETE, OrderPaymentFragment())))
        return list
    }

    private fun addParams(paymentId: String, paymentType: String, fragment: Fragment): Fragment {
        fragment.arguments = Bundle()
        fragment.arguments.putString(OrderPaymentFragment.PAYMENT_ID, paymentId)
        fragment.arguments.putString(OrderPaymentFragment.PAYMENT_TYPE, paymentType)
        return fragment;
    }
}