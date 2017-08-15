package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.ViewPagerTabsAdapter
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtSettingsVC

/**
 * Created by root on 03.06.17.
 */

class SettingsFragment: BaseParentFragment<FmtSettingsVC>() {

    companion object {
        val SETTINGS_FRAGMENT_ID: String = "main.settings_fragment"
    }

    @BindView(R.id.fmt_settings_tb_tabs)
    lateinit var mTlTabs: TabLayout
    @BindView(R.id.fmt_settings_vp_body)
    lateinit var mVpBody: ViewPager

    private lateinit var mAdapter: ViewPagerTabsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_settings, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtSettingsVC(this)
        mTlTabs.setupWithViewPager(mVpBody)
        mAdapter = ViewPagerTabsAdapter(this.childFragmentManager)
        mVpBody.setAdapter(mAdapter)
        mViewController?.start()
    }

    override fun onResume() {
        super.onResume()
        getToolbar().setTitle(getString(R.string.settings))
    }

    fun loadData(data: List<ViewPagerItemContainer>) {
        mAdapter.loadData(data)
    }
}
