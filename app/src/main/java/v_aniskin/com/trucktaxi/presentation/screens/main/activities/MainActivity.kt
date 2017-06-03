package v_aniskin.com.trucktaxi.presentation.screens.main.activities


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
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

    companion object {
        private const val LAST_SCREEN_KEY: String = "lastscreenkey"
    }

    @BindView(R.id.ac_main_toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.ac_main_bottom_navigation)
    lateinit var mBottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this)
        mViewController = AcMainVC(this)
        mBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> {
                    mViewController?.showNewScreenChain(HOME_FRAGMENT_ID)
                    true
                }
                R.id.action_orders -> {
                    mViewController?.showNewScreenChain(ORDERS_FRAGMENT_ID)
                    true
                }
                R.id.action_payments -> {
                    mViewController?.showNewScreenChain(PAYMENTS_FRAGMENT_ID)
                    true
                }
                R.id.action_chat -> {
                    mViewController?.showNewScreenChain(CHAT_FRAGMENT_ID)
                    true
                }
                R.id.action_settings -> {
                    mViewController?.showNewScreenChain(SETTINGS_FRAGMENT_ID)
                    true
                }
                else -> false
            }
        }
        mViewController?.start()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val lastStringKey: String? = savedInstanceState?.getString(LAST_SCREEN_KEY)
        if (lastStringKey != null) {
            mViewController?.showNewScreenChain(lastStringKey)
            mBottomNavigation.menu.getItem(MainFragmentFactory.getMenuPositionByScreenKey(lastStringKey)).setChecked(true)
        }
    }

    override fun onSaveInstanceState(state: Bundle?) {
        super.onSaveInstanceState(state)
        val lastStringKey: String? = mViewController?.getLastScreenKey()
        if (lastStringKey != null)
            state?.putString(LAST_SCREEN_KEY, lastStringKey)
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

    //Getters and Setters
    fun getContainerId(): Int {
        return R.id.ac_main_fl_fragment_container
    }
}
