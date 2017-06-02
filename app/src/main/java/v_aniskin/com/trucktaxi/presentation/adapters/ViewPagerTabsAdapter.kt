package v_aniskin.com.trucktaxi.presentation.adapters

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer

/**
 * Created by root on 13.12.16.
 */
class ViewPagerTabsAdapter(fragmentManager: FragmentManager, private val mTabLayout: TabLayout, private val mViewPager: ViewPager) : FragmentPagerAdapter(fragmentManager) {

    private var mData: List<ViewPagerItemContainer> = emptyList<ViewPagerItemContainer>()

    private val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            mViewPager.currentItem = tab.position
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {

        }

        override fun onTabReselected(tab: TabLayout.Tab) {

        }
    }

    init {
        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(tabSelectedListener)
        mTabLayout.setupWithViewPager(mViewPager, true)
    }

    override fun getItem(position: Int): Fragment? {
        return mData.get(position).fragment
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mViewPager.context.getString(mData.get(position).tabTitleResource)
    }

    fun loadData(data: List<ViewPagerItemContainer>) {
        mData = data
        notifyDataSetChanged()
    }

    fun getItemData(position: Int): Any? {
        return mData.get(position).data
    }
}
