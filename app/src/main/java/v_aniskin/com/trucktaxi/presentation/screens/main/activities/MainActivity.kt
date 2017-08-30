package v_aniskin.com.trucktaxi.presentation.screens.main.activities


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.AcMainVC
import v_aniskin.com.trucktaxi.R;
import v_aniskin.com.trucktaxi.presentation.factories.MainFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.ChatFragment.Companion.CHAT_FRAGMENT_ID
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.HomeFragment.Companion.HOME_FRAGMENT_ID
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.OrdersFragment.Companion.ORDERS_FRAGMENT_ID
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.PaymentsFragment.Companion.PAYMENTS_FRAGMENT_ID
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.SettingsFragment.Companion.SETTINGS_FRAGMENT_ID

class MainActivity : BaseActivity<AcMainVC>() {

    @BindView(R.id.ac_main_toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.ac_main_bottom_navigation)
    lateinit var mBottomNavigation: BottomNavigationView
    @BindView(R.id.ac_main_progress)
    lateinit var mProgress: ProgressBar

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this)
        mViewController = AcMainVC(this)
        mBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> {
                    mViewController?.replaceFragmentScreen(HOME_FRAGMENT_ID)
                    true
                }
                R.id.action_orders -> {
                    mViewController?.replaceFragmentScreen(ORDERS_FRAGMENT_ID)
                    true
                }
                R.id.action_payments -> {
                    mViewController?.replaceFragmentScreen(PAYMENTS_FRAGMENT_ID)
                    true
                }
                R.id.action_chat -> {
                    mViewController?.replaceFragmentScreen(CHAT_FRAGMENT_ID)
                    true
                }
                R.id.action_settings -> {
                    mViewController?.replaceFragmentScreen(SETTINGS_FRAGMENT_ID)
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null)
            mViewController?.start()
    }

    override fun getToolbar(): Toolbar {
        return mToolbar
    }

    override fun getBottomNavigation(): BottomNavigationView {
        return mBottomNavigation
    }

    override fun onResume() {
        super.onResume()
        mViewController?.resume()

    }

    override fun  onPause() {
        super.onPause()
        mViewController?.pause()
    }

    fun getContainerId(): Int {
        return R.id.ac_main_fl_fragment_container
    }

    fun startProgress() {
        mProgress.visibility = View.VISIBLE
    }

    fun stopProgress() {
        mProgress.visibility = View.GONE
    }
}
