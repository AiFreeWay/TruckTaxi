package v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.balysv.materialmenu.MaterialMenuDrawable
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.models.OrderPresent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers.AcMapOrderDetailVC

/**
 * Created by root on 31.05.17.
 */
class MapOrderDetailActivity : BaseActivity<AcMapOrderDetailVC>() {

    companion object {
        val MAP_ORDER_DETAIL_ACTIVITY_ID: String = "orderdetail.maporderdetailactivity"
    }

    @BindView(R.id.ac_map_order_details_mv_map)
    lateinit var mMvMap: MapView
    @BindView(R.id.ac_map_order_details_btn_info)
    lateinit var mBtnInfo: AppCompatButton
    @BindView(R.id.ac_map_order_details_toolbar)
    lateinit var mToolbar: Toolbar

    private lateinit var mMenuDrawer: MaterialMenuIconToolbar


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_map_order_details);
        ButterKnife.bind(this)
        mViewController = AcMapOrderDetailVC(this)
        initToolbar()
        mMvMap.onCreate(savedInstanceState)
        mMvMap.getMapAsync({map -> doOnGetMap(map)})
        mBtnInfo.setOnClickListener { mViewController?.showOrdersDetailScreen(OrderPresent.STATE_CURRENT) }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMvMap.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMvMap.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mMvMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMvMap.onPause()
    }

    override fun getToolbar(): Toolbar {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBottomNavigation(): BottomNavigationView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                return R.id.ac_map_order_details_toolbar;
            }
        }
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }
}