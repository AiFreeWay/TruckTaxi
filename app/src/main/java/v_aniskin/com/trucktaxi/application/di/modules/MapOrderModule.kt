package v_aniskin.com.trucktaxi.application.di.modules

import dagger.Module
import dagger.Provides
import v_aniskin.com.trucktaxi.application.di.scopes.PerMapOrder
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.data.location.LocationController
import v_aniskin.com.trucktaxi.domain.executors.LocationExecutorImpl
import v_aniskin.com.trucktaxi.domain.executors.OrdersExecutorImpl
import v_aniskin.com.trucktaxi.domain.executors.interfaces.LocationExecutor
import v_aniskin.com.trucktaxi.domain.executors.interfaces.OrdersExecutor
import v_aniskin.com.trucktaxi.presentation.screens.order_detail.activities.MapOrderDetailActivity

/**
 * Created by root on 25.08.17.
 */
@Module
class MapOrderModule(private val mActivity: MapOrderDetailActivity) {

    init {
        Logger.testLog("MapOrderModule create")
    }

    @Provides
    @PerMapOrder
    fun provideLocationController() : LocationController {
        return LocationController(mActivity, mActivity)
    }

    @Provides
    @PerMapOrder
    fun provideLocationExecutor(locationExecutor: LocationExecutorImpl) : LocationExecutor {
        return locationExecutor
    }

    @Provides
    @PerMapOrder
    fun provideOrdersExecutor(ordersExecutor: OrdersExecutorImpl) : OrdersExecutor {
        return ordersExecutor
    }
}