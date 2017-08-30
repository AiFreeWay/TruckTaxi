package v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities

import android.Manifest
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.pm.PackageManager
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
import v_aniskin.com.trucktaxi.application.utils.OrdersTypes
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.view_controllers.AcMapOrderDetailVC
import v_aniskin.com.trucktaxi.presentation.utils.PermissonChecker
import android.content.IntentSender
import com.google.android.gms.common.api.Status
import v_aniskin.com.trucktaxi.application.utils.Logger
import android.content.Intent
import android.widget.Toast
import v_aniskin.com.trucktaxi.data.location.LocationController


/**
 * Created by root on 31.05.17.
 */
class MapOrderDetailActivity : BaseActivity<AcMapOrderDetailVC>(), LifecycleRegistryOwner, LocationController.LocationInstaller {

    companion object {
        val MAP_ORDER_DETAIL_ACTIVITY_ID: String = "orderdetail.maporderdetailactivity"
        val ORDER_ID_KEY = "order_id"
        val PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 12
        val LOCATION_RESOLUTION = 15
    }

    @BindView(R.id.ac_map_order_details_mv_map)
    lateinit var mMvMap: MapView
    @BindView(R.id.ac_map_order_details_btn_info)
    lateinit var mBtnInfo: AppCompatButton
    @BindView(R.id.ac_map_order_details_toolbar)
    lateinit var mToolbar: Toolbar

    private lateinit var mMenuDrawer: MaterialMenuIconToolbar
    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)
    private lateinit var mMap: GoogleMap
    private lateinit var mOrderId: String

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_map_order_details);
        ButterKnife.bind(this)
        mOrderId = intent.getStringExtra(ORDER_ID_KEY)
        mViewController = AcMapOrderDetailVC(this)
        initToolbar()
        mMvMap.onCreate(savedInstanceState)
        mMvMap.getMapAsync({map -> doOnGetMap(map)})
        mBtnInfo.setOnClickListener { mViewController?.showOrdersDetailScreen(OrdersTypes.ORDER_STATUS_CURRENT) }
    }

    override fun getLifecycle(): LifecycleRegistry = mLifecycle

    override fun onStop() {
        super.onStop()
        mViewController?.stop()
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                mViewController?.loadGoogleMap(mMap)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (LOCATION_RESOLUTION == requestCode) {
            if (RESULT_OK == resultCode) {
                Logger.testLog("onActivityResult")
                mViewController?.startLocationUpdate()
            } else
                Toast.makeText(this, R.string.need_location_enabled, Toast.LENGTH_SHORT).show()
        }
    }

    override fun sendLocationStatus(status: Status) {
        try {
            status.startResolutionForResult(this, LOCATION_RESOLUTION)
        } catch (e: IntentSender.SendIntentException) {
            Logger.logError(e)
        }
    }

    fun getOrderId(): String = mOrderId

    private fun doOnGetMap(map: GoogleMap) {
        map.getUiSettings().setAllGesturesEnabled(true)
        map.getUiSettings().setCompassEnabled(true)
        map.getUiSettings().setZoomControlsEnabled(true)
        mMap = map
        if (checkLocationPermission())
            mViewController?.loadGoogleMap(mMap)
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

    fun checkLocationPermission(): Boolean {
        return PermissonChecker.checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, this, PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION)
    }
}