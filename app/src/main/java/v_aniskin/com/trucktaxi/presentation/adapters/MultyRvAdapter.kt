package v_aniskin.com.trucktaxi.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import v_aniskin.com.trucktaxi.presentation.adapters.holders.BaseHolder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryHolder
import v_aniskin.com.trucktaxi.presentation.models.AdapterItemContainer
import java.util.*

/**
 * Created by root on 28.05.17.
 */
class MultyRvAdapter<M, VC>: RecyclerView.Adapter<BaseHolder<M, VC>> {

    companion object {
        var VIEW_TYPE_HOLDER: Int = 0;
        var VIEW_TYPE_HEADER: Int = 1;
    }

    private var mData: List<AdapterItemContainer<M>>
    private var mBaseHolderTemplate: BaseHolder<M, VC>
    private var mHeaderTemplate: SubsidaryHolder<M, VC>? = null

    constructor (baseHolderTemplate: BaseHolder<M, VC>) {
        mData = Collections.emptyList()
        mBaseHolderTemplate = baseHolderTemplate;
    }

    constructor (headerTemplate: SubsidaryHolder<M, VC>, baseHolderTemplate: BaseHolder<M, VC>) {
        mData = Collections.emptyList();
        mHeaderTemplate = headerTemplate;
        mBaseHolderTemplate = baseHolderTemplate;
    }

    override fun onBindViewHolder(holder: BaseHolder<M, VC>, position: Int) {
        holder.bind(mData.get(position).data);
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<M, VC>? {
        when (viewType) {
            VIEW_TYPE_HEADER -> return mHeaderTemplate?.create(parent);
            else -> return mBaseHolderTemplate?.create(parent);
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mData.get(position).type
    }

    fun loadData(data: List<AdapterItemContainer<M>>) {
        mData = data;
        notifyDataSetChanged();
    }
}