package v_aniskin.com.trucktaxi.presentation.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer

/**
 * Created by root on 13.12.16.
 */
class ViewPagerTabsAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var mData: List<ViewPagerItemContainer> = emptyList<ViewPagerItemContainer>()

    override fun getItem(position: Int): Fragment? {
        return mData.get(position).fragment
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mData.get(position).tabTitle
    }

    fun loadData(data: List<ViewPagerItemContainer>) {
        mData = data
        notifyDataSetChanged()
    }
}
