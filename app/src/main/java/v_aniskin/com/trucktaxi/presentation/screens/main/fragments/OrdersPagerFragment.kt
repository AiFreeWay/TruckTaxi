package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.ViewPagerTabsAdapter
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtOrdersPagerVC

/**
 * Created by root on 07.08.17.
 */

class OrdersPagerFragment : BaseParentFragment<FmtOrdersPagerVC>()  {

    @BindView(R.id.fmt_view_pager_tb_tabs)
    lateinit var mTbTabs: TabLayout
    @BindView(R.id.fmt_view_pager_vp_body)
    lateinit var mVpOrders: ViewPager

    private lateinit var mAdapter: ViewPagerTabsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_view_pager, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtOrdersPagerVC(this)
        mTbTabs.setupWithViewPager(mVpOrders);
        mAdapter = ViewPagerTabsAdapter(childFragmentManager)
        mVpOrders.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        getToolbar().setTitle(getString(R.string.orders))
    }

    override fun onStart() {
        super.onStart()
        mViewController?.start()
    }

    fun loadOrdersFragments(data: List<ViewPagerItemContainer>) {
        mAdapter.loadData(data)
    }
}
