package v_aniskin.com.trucktaxi.presentation.factories

import android.content.Context
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.fragmens.OrderPaymentFragment
import java.util.*

/**
 * Created by root on 01.06.17.
 */
class OrdersPaymentsFragmentFactory {

    fun getAllItems(context: Context): List<ViewPagerItemContainer> {
        val list: ArrayList<ViewPagerItemContainer> = ArrayList<ViewPagerItemContainer>()
        list.add(ViewPagerItemContainer(context.getString(R.string.requistion), OrderPaymentFragment()))
        list.add(ViewPagerItemContainer(context.getString(R.string.fact), OrderPaymentFragment()))
        return list
    }
}