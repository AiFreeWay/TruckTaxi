package v_aniskin.com.trucktaxi.presentation.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import v_aniskin.com.trucktaxi.presentation.adapters.binders.BaseBinder
import java.util.*

/**
 * Created by root on 27.05.17.
 */
class MultyGridViewAdapter<M, B: BaseBinder<M>>(private var mBinder: B): BaseAdapter() {

    private var mData: List<M> = Collections.emptyList()


    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View {
        return mBinder.bind(getItem(p0), p2)
    }

    override fun getItem(p0: Int): M {
        return mData.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

    fun loadData(data: List<M>) {
        mData = data
        notifyDataSetChanged()
    }
}