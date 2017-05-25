package v_aniskin.com.trucktaxi.presentation.screens.common

/**
 * Created by root on 25.05.17.
 */
abstract class BaseViewController<V>(view: V) {

    protected var mView: V? = view

    open fun start() {

    }

    open fun stop() {

    }

    fun getView(): V? {
        return mView;
    }
}