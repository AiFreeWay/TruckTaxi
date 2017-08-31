package v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.*
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.balysv.materialmenu.MaterialMenuDrawable
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.OrdersTypes
import v_aniskin.com.trucktaxi.domain.models.Order
import v_aniskin.com.trucktaxi.domain.models.OrderRoutePoint
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.adapters.ViewPagerTabsAdapter
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers.AcOrderDetailVC

/**
 * Created by root on 01.06.17.
 */
class OrderDetailActivity : BaseActivity<AcOrderDetailVC>() {

    companion object {
        val ORDER_DETAIL_ACTIVITY_ID: String = "orderdetail.orderdetailactivity"
        val ORDER_ID_KEY = "order_id"
    }

    @BindView(R.id.ac_order_details_progress)
    lateinit var mProgress: ProgressBar
    @BindView(R.id.ac_order_details_toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.ac_order_details_mv_map)
    lateinit var mMvMap: MapView
    @BindView(R.id.ac_order_details_tb_payments_types)
    lateinit var mTlTabs: TabLayout
    @BindView(R.id.ac_order_details_vp_payments)
    lateinit var mVpBody: ViewPager
    @BindView(R.id.ac_order_details_tv_from)
    lateinit var mTvFrom: TextView
    @BindView(R.id.ac_order_details_tv_phone_from)
    lateinit var mTvPhoneFrom: TextView
    @BindView(R.id.ac_order_details_tv_addresses)
    lateinit var mTvAddresses: TextView
    @BindView(R.id.ac_order_details_ll_addreses)
    lateinit var mLlAddresses: LinearLayout

    private lateinit var mMenuDrawer: MaterialMenuIconToolbar
    private lateinit var mAdapter: ViewPagerTabsAdapter
    private var mStatus: String = ""
    private var mOrderId: String = ""

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_order_details);
        ButterKnife.bind(this)
        mStatus = intent.getStringExtra(ORDER_DETAIL_ACTIVITY_ID)
        mOrderId = intent.getStringExtra(ORDER_ID_KEY)
        mViewController = AcOrderDetailVC(this)
        mTlTabs.setupWithViewPager(mVpBody)
        mAdapter = ViewPagerTabsAdapter(getSupportFragmentManager())
        mVpBody.setAdapter(mAdapter)
        initToolbar()
        if (mStatus == OrdersTypes.ORDER_STATUS_APPOINTED) {
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
        if (mStatus == OrdersTypes.ORDER_STATUS_APPOINTED)
            inflater.inflate(R.menu.new_order_detail_menu, menu)
        else
            inflater.inflate(R.menu.order_detail_menu, menu)
        return true
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (mStatus == OrdersTypes.ORDER_STATUS_APPOINTED)
            mMvMap.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mStatus == OrdersTypes.ORDER_STATUS_APPOINTED)
            mMvMap.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        if (mStatus == OrdersTypes.ORDER_STATUS_APPOINTED)
            mMvMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (mStatus == OrdersTypes.ORDER_STATUS_APPOINTED)
            mMvMap.onPause()
    }

    override fun getToolbar(): Toolbar = mToolbar

    override fun getBottomNavigation(): BottomNavigationView {
        throw Exception("BottomNavigation not implemented here v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.OrderDetailActivity")
    }

    fun loadOrder(order: Order) {
        mLlAddresses.removeAllViews()
        if (order.orderRoutepoints.size > 1)
            mTvAddresses.visibility = View.VISIBLE
        else
            mTvAddresses.visibility = View.GONE

        for (i: Int in order.orderRoutepoints.indices) {
            val orderPoint = order.orderRoutepoints[i]
            if (i == 0)
                loadStartPointData(orderPoint)
            else
                loadDestinationPointData(orderPoint)
        }
    }

    fun loadData(data: List<ViewPagerItemContainer>) {
        mAdapter.loadData(data)
    }

    fun startProgress() {
        mProgress.visibility = View.VISIBLE
    }

    fun stopProgress() {
        mProgress.visibility = View.GONE
    }

    fun showToast(text: Int) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun getOrderId() = mOrderId

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun loadStartPointData(point: OrderRoutePoint) {
        val address = point.routepointContactAddress
        val phone = point.routepointContactPhone

        if (!TextUtils.isEmpty(address)) {
            mTvFrom.visibility = View.VISIBLE
            mTvFrom.setText(address)
        } else
            mTvFrom.visibility = View.GONE

        if (!TextUtils.isEmpty(phone)) {
            mTvPhoneFrom.visibility = View.VISIBLE
            mTvPhoneFrom.setText(phone)
        } else
            mTvPhoneFrom.visibility = View.GONE
    }

    private fun loadDestinationPointData(point: OrderRoutePoint) {
        val fromTextView = createTextView(mLlAddresses)
        val fromPhoneTextView = createTextView(mLlAddresses)
        fromTextView.setText(point.routepointContactAddress)
        fromPhoneTextView.setText(point.routepointContactPhone)
        mLlAddresses.addView(fromTextView)
        mLlAddresses.addView(fromPhoneTextView)
    }

    private fun doOnGetMap(map: GoogleMap) {
        map.getUiSettings().setAllGesturesEnabled(true)
        map.getUiSettings().setCompassEnabled(true)
        map.getUiSettings().setZoomControlsEnabled(true)
    }

    private fun createTextView(viewGroup: ViewGroup): TextView =
            LayoutInflater.from(this).inflate(R.layout.v_text_view, viewGroup, false) as TextView

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