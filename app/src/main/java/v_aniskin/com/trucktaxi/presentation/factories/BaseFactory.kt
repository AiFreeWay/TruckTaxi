package v_aniskin.com.trucktaxi.presentation.factories

/**
 * Created by root on 25.05.17.
 */
interface BaseFactory<T> {

    fun createInstanse(key: String): T
}