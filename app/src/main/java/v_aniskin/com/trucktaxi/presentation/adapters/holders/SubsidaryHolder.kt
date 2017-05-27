package v_aniskin.com.trucktaxi.presentation.adapters.holders

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup

/**
 * Created by root on 14.12.16.
 */
open class SubsidaryHolder<M, VC> : BaseHolder<M, VC> {

    private var mBinder: SubsidaryBinder<M>? = null
    private var mLayoutRes: Int = 0

    constructor(context: Context, @LayoutRes layout: Int) : super(context, null) {
        mLayoutRes = layout
    }

    constructor(context: Context, @LayoutRes layout: Int, binder: SubsidaryBinder<M>?) : super(context, null) {
        mLayoutRes = layout
        mBinder = binder
    }

    constructor(view: View) : super(view, null) {}

    constructor(view: View, binder: SubsidaryBinder<M>) : super(view, null) {
        mBinder = binder
    }

    override fun create(viewGroup: ViewGroup): SubsidaryHolder<M, VC> {
        val view = viewInflater(viewGroup, mLayoutRes)
        if (mBinder != null)
            return SubsidaryHolder<M, VC>(view, mBinder as SubsidaryBinder<M>)
        return SubsidaryHolder(view)
    }

    override fun bind(dataModel: M) {
        if (mBinder != null)
            mBinder!!.bind(mView, dataModel)
    }
}
