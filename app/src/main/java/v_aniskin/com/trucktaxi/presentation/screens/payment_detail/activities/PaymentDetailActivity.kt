package v_aniskin.com.trucktaxi.presentation.screens.payment_detail.activities

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.balysv.materialmenu.MaterialMenuDrawable
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.addons.ViewPagerItemContainer
import v_aniskin.com.trucktaxi.presentation.adapters.ViewPagerTabsAdapter
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.payment_detail.view_controllers.AcPaymentDetailVC

/**
 * Created by root on 01.06.17.
 */
class PaymentDetailActivity : BaseActivity<AcPaymentDetailVC>() {

    companion object {
        val PAYMENT_DETAIL_ACTIVITY_ID: String = "paymentdetail.paymentdetailactivity"
    }

    @BindView(R.id.ac_payment_detail_toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.ac_payment_details_tb_payments_types)
    lateinit var mTlTabs: TabLayout
    @BindView(R.id.ac_payment_details_vp_payments)
    lateinit var mVpBody: ViewPager

    private lateinit var mMenuDrawer: MaterialMenuIconToolbar
    private lateinit var mAdapter: ViewPagerTabsAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_payment_detail);
        ButterKnife.bind(this)
        mViewController = AcPaymentDetailVC(this)
        mAdapter = ViewPagerTabsAdapter(getSupportFragmentManager(), mTlTabs, mVpBody)
        mVpBody.setAdapter(mAdapter)
        initToolbar()
        mViewController?.start()
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

    private fun initToolbar() {
        setSupportActionBar(mToolbar)
        getSupportActionBar()?.setTitle(getString(R.string.test_order_number))
        mToolbar.setNavigationOnClickListener({ finish() })
        mMenuDrawer = object: MaterialMenuIconToolbar(this, Color.BLACK, MaterialMenuDrawable.Stroke.THIN) {

            override fun getToolbarViewId(): Int {
                return R.id.ac_payment_detail_toolbar;
            }
        }
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }
}