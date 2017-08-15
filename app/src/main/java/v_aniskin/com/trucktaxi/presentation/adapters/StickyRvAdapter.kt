package v_aniskin.com.trucktaxi.presentation.adapters

import android.view.ViewGroup

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.adapters.addons.StickyPredicat
import v_aniskin.com.trucktaxi.presentation.adapters.holders.BaseHolder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryHolder

/**
 * Created by root on 02.06.17.
 */

class StickyRvAdapter<M, VC>(protected var mStickyTemplate: SubsidaryHolder<M, VC>, baseHolderTemplate: BaseHolder<M, VC>, protected var mPredicat: StickyPredicat<M>) : MultyRvAdapter<M, VC>(baseHolderTemplate), StickyHeaderAdapter<SubsidaryHolder<M, VC>> {

    override fun getHeaderId(position: Int): Long {
        return mPredicat.call(getItem(position))
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): SubsidaryHolder<M, VC> {
        return mStickyTemplate.create(parent)
    }

    override fun onBindHeaderViewHolder(holder: SubsidaryHolder<M, VC>, position: Int) {
        holder.bind(getItem(position))
    }
}