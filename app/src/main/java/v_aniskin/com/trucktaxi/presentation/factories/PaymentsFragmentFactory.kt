package v_aniskin.com.trucktaxi.presentation.factories

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.PaymentsType
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.fragments.PaymentFragment
import java.util.*

/**
 * Created by root on 01.06.17.
 */
class PaymentsFragmentFactory {

    fun getAllItems(paymentId: String, context: Context): List<ViewPagerItemContainer> {
        val list: ArrayList<ViewPagerItemContainer> = ArrayList<ViewPagerItemContainer>()
        list.add(ViewPagerItemContainer(context.getString(R.string.requistion), addParams(paymentId, PaymentsType.ORDER_STATUS_FUTURE, PaymentFragment())))
        list.add(ViewPagerItemContainer(context.getString(R.string.fact), addParams(paymentId, PaymentsType.ORDER_STATUS_COMPLETE, PaymentFragment())))
        return list
    }

    private fun addParams(paymentId: String, paymentType: String, fragment: Fragment): Fragment {
        fragment.arguments = Bundle()
        fragment.arguments.putString(PaymentFragment.PAYMENT_ID, paymentId)
        fragment.arguments.putString(PaymentFragment.PAYMENT_TYPE, paymentType)
        return fragment;
    }
}