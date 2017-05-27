package v_aniskin.com.trucktaxi.presentation.adapters.binders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by root on 27.05.17.
 */
abstract class BaseBinder<M>(private var mContext: Context) {

    protected lateinit var mView: View

    fun inflate(resourse: Int, viewGroup: ViewGroup): View {
        return LayoutInflater.from(mContext).inflate(resourse, viewGroup, false)
    }

    abstract fun bind(data: M, viewGroup: ViewGroup): View
}