package v_aniskin.com.trucktaxi.application.utils

import rx.Subscription
import java.util.*

/**
 * Created by root on 02.08.17.
 */
class SubscriptionContainer {

    private val mSubscriptions: ArrayList<Subscription> = ArrayList();

    fun addSubscription(subscription: Subscription) {
        mSubscriptions.add(subscription)
    }

    fun unsubscribeAll() {
        mSubscriptions.forEach {
            try {
                it.unsubscribe()
            } catch (e: Exception) {
                Logger.logError(e)
            }
        }
    }
}