package v_aniskin.com.trucktaxi.presentation.screens.main.activities


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.AcMainVC
import v_aniskin.com.trucktaxi.R;

class MainActivity : BaseActivity<AcMainVC>() {

    @BindView(R.id.ac_main_toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.ac_main_bottom_navigation)
    lateinit var mBottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this)
        mViewController = AcMainVC(this)
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

    //Getters and Setters
    fun getContainerId(): Int {
        return R.id.ac_main_fl_fragment_container
    }
}
