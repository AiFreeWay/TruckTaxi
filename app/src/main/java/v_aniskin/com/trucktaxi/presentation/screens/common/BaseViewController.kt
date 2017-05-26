package v_aniskin.com.trucktaxi.presentation.screens.common

/**
 * Created by root on 25.05.17.
 */
abstract class BaseViewController<V>(view: V) {

    protected var mView: V = view

    init {
        inject()
    }

    open fun start() {

    }

    open fun resume() {

    }

    open fun pause() {

    }

    open fun stop() {

    }

    open fun inject() {

    }

    fun getView(): V? {
        return mView;
    }
}