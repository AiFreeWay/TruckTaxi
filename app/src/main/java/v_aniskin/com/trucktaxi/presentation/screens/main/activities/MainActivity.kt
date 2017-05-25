package v_aniskin.com.trucktaxi.presentation.screens.main.activities


import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import v_aniskin.com.trucktaxi.application.di.components.MainScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.MainScreenModule
import v_aniskin.com.trucktaxi.presentation.navigators.FragmentNavigator
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.MainAcVC
import javax.inject.Inject
import javax.inject.Named
import v_aniskin.com.trucktaxi.R;
import v_aniskin.com.trucktaxi.application.di.components.DaggerMainScreenComponent
import v_aniskin.com.trucktaxi.application.utils.Logger

class MainActivity : BaseActivity<MainAcVC>() {

    @BindView(R.id.ac_main_toolbar)
    lateinit var mToolbar: Toolbar

    @Inject
    lateinit var mRouter: Router
    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder
    @Inject
    lateinit var mFragmentNavigator: FragmentNavigator

    private var mMainScreenComponent: MainScreenComponent? = null
    private var isCiceroneInit = false

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this)
        inject()
        initCicerone()
        mViewController = MainAcVC(this)
        mViewController?.start()
    }

    override fun onResume() {
        super.onResume()
        initCicerone()
    }

    override fun  onPause() {
        super.onPause();
        mNavigatorHolder.removeNavigator();
        isCiceroneInit = false
    }

    private fun inject() {
        mMainScreenComponent = DaggerMainScreenComponent.builder()
                .screenComponent(mScreenComponent)
                .mainScreenModule(MainScreenModule(this))
                .build()
        mMainScreenComponent?.inject(this)
    }

    private fun initCicerone() {
        if (mMainScreenComponent != null && !isCiceroneInit) {
            mNavigatorHolder.setNavigator(mFragmentNavigator)
            isCiceroneInit = true
        }
    }

    //Getters and Setters
    fun getNavigatorHolder(): FragmentNavigator {
        return mFragmentNavigator
    }

    fun getRouter(): Router  {
        return mRouter
    }

    fun getContainerId(): Int {
        return R.id.ac_main_fl_fragment_container;
    }
}
