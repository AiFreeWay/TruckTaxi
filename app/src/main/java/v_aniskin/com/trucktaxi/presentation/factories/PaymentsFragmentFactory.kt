package v_aniskin.com.trucktaxi.presentation.factories

import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.fragments.PaymentFragment
import java.util.*

/**
 * Created by root on 01.06.17.
 */
class PaymentsFragmentFactory {

    fun getAllItems(): List<ViewPagerItemContainer> {
        val list: ArrayList<ViewPagerItemContainer> = ArrayList<ViewPagerItemContainer>()
        list.add(ViewPagerItemContainer(R.string.requistion, PaymentFragment()))
        list.add(ViewPagerItemContainer(R.string.fact, PaymentFragment()))
        return list
    }
}