package v_aniskin.com.trucktaxi.presentation.adapters.holders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by root on 13.12.16.
 */
abstract class BaseHolder<M, VC> : RecyclerView.ViewHolder {

    protected var mViewController: VC?
    protected lateinit var mView: View
    protected var mContext: Context

    constructor(context: Context, viewController: VC?) : super(View(context)) {
        mViewController = viewController
        mContext = context
    }

    constructor(itemView: View, viewController: VC?) : super(itemView) {
        mViewController = viewController
        mView = itemView
        mContext = itemView.context
    }

    protected fun viewInflater(viewGroup: ViewGroup, resLayout: Int): View {
        val layoutInflater = LayoutInflater.from(mContext)
        return layoutInflater.inflate(resLayout, viewGroup, false)
    }

    open fun bind() {

    }

    abstract fun create(viewGroup: ViewGroup): BaseHolder<M, VC>

    abstract fun bind(dataModel: M)
}
