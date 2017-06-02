package v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.VISIBLE
import butterknife.BindView
import butterknife.ButterKnife
import com.balysv.materialmenu.MaterialMenuDrawable
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.adapters.ViewPagerTabsAdapter
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers.AcOrderDetailVC

/**
 * Created by root on 01.06.17.
 */
class OrderDetailActivity : BaseActivity<AcOrderDetailVC>() {

    companion object {
        val ORDER_DETAIL_ACTIVITY_ID: String = "orderdetail.orderdetailactivity"
    }

    @BindView(R.id.ac_order_details_toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.ac_order_details_mv_map)
    lateinit var mMvMap: MapView
    @BindView(R.id.ac_order_details_tb_payments_types)
    lateinit var mTlTabs: TabLayout
    @BindView(R.id.ac_order_details_vp_payments)
    lateinit var mVpBody: ViewPager

    private lateinit var mMenuDrawer: MaterialMenuIconToolbar
    private lateinit var mAdapter: ViewPagerTabsAdapter
    private var mState: Int = -1

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_order_details);
        ButterKnife.bind(this)
        mState = intent.getIntExtra(ORDER_DETAIL_ACTIVITY_ID, -1)
        mViewController = AcOrderDetailVC(this)
        mAdapter = ViewPagerTabsAdapter(getSupportFragmentManager(), mTlTabs, mVpBody)
        mVpBody.setAdapter(mAdapter)
        initToolbar()
        if (mState == OrderPresent.STATE_NEW) {
            mMvMap.visibility = VISIBLE
            mMvMap.onCreate(savedInstanceState)
            mMvMap.getMapAsync({ map -> doOnGetMap(map) })
        }
        mViewController?.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        if (mState == OrderPresent.STATE_NEW)
            inflater.inflate(R.menu.new_order_detail_menu, menu)
        else
            inflater.inflate(R.menu.order_detail_menu, menu)
        return true
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (mState == OrderPresent.STATE_NEW)
            mMvMap.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mState == OrderPresent.STATE_NEW)
            mMvMap.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        if (mState == OrderPresent.STATE_NEW)
            mMvMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (mState == OrderPresent.STATE_NEW)
            mMvMap.onPause()
    }

    override fun getToolbar(): Toolbar {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBottomNavigation(): BottomNavigationView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun loadData(data: List<ViewPagerItemContainer>) {
        mAdapter.loadData(data)
    }

    private fun doOnGetMap(map: GoogleMap) {
        map.getUiSettings().setAllGesturesEnabled(true)
        map.getUiSettings().setCompassEnabled(true)
        map.getUiSettings().setZoomControlsEnabled(true)
    }

    private fun initToolbar() {
        setSupportActionBar(mToolbar)
        getSupportActionBar()?.setTitle(getString(R.string.test_order_number))
        mToolbar.setNavigationOnClickListener({ finish() })
        mMenuDrawer = object: MaterialMenuIconToolbar(this, Color.BLACK, MaterialMenuDrawable.Stroke.THIN) {

            override fun getToolbarViewId(): Int {
                return R.id.ac_order_details_toolbar;
            }
        }
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }
}