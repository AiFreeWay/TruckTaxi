package v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers

import v_aniskin.com.trucktaxi.application.di.components.DaggerSettingsScreenComponent
import v_aniskin.com.trucktaxi.application.di.components.SettingsScreenComponent
import v_aniskin.com.trucktaxi.application.di.modules.SettingsScreenModule
import v_aniskin.com.trucktaxi.presentation.factories.SettingsFragmentFactory
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseViewController
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity
import v_aniskin.com.trucktaxi.presentation.screens.main.fragments.SettingsFragment
import javax.inject.Inject

/**
 * Created by root on 03.06.17.
 */
class FmtSettingsVC(fragment: SettingsFragment) : BaseViewController<SettingsFragment>(fragment) {

    @Inject
    lateinit var mFactory: SettingsFragmentFactory

    private var mSettingsScreenComponent: SettingsScreenComponent? = null

    override fun inject() {
        mSettingsScreenComponent = DaggerSettingsScreenComponent.builder()
                .screenComponent(mView.getScreenComponet())
                .settingsScreenModule(SettingsScreenModule())
                .build()
        mSettingsScreenComponent?.inject(this)
    }

    override fun start() {
        super.start()
        mView.loadData(mFactory.getAllItems())
    }

    fun getAcMainVC(): AcMainVC? {
        return getView()?.getBaseActivity<MainActivity>()
                ?.getViewController()
    }
}