package v_aniskin.com.trucktaxi.application.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.executors.AuthExecutorImpl
import v_aniskin.com.trucktaxi.domain.executors.interfaces.AuthExecutor
import v_aniskin.com.trucktaxi.presentation.factories.MainFragmentFactory
import v_aniskin.com.trucktaxi.presentation.navigators.MainFragmentNavigator
import v_aniskin.com.trucktaxi.presentation.screens.main.activities.MainActivity

/**
 * Created by root on 25.05.17.
 */
@Module
class MainScreenModule(private val mActivity: MainActivity) {

    private val mCicerone: Cicerone<Router>

    init {
        Logger.testLog("MainScreenModule create")
        mCicerone = Cicerone.create()
    }

    @Provides
    fun provideNavigatorHolder() : NavigatorHolder {
        return mCicerone.getNavigatorHolder()
    }

    @Provides
    fun provideRouter() : Router {
        return mCicerone.getRouter()
    }

    @Provides
    fun provideMainFragmentFactory() : MainFragmentFactory {
        return MainFragmentFactory()
    }

    @Provides
    fun provideFragmentNavigator(fragmentFactory: MainFragmentFactory) : MainFragmentNavigator {
        return MainFragmentNavigator(mActivity, fragmentFactory, mActivity.supportFragmentManager, mActivity.getContainerId())
    }

    @Provides
    fun provideAuthExecutor(authExecutor: AuthExecutorImpl) : AuthExecutor {
        return authExecutor
    }
}