package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.presentation.adapters.StickyRvAdapter
import v_aniskin.com.trucktaxi.presentation.adapters.addons.StickyPredicat
import v_aniskin.com.trucktaxi.presentation.adapters.holders.ChatHolder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryBinder
import v_aniskin.com.trucktaxi.presentation.adapters.holders.SubsidaryHolder
import v_aniskin.com.trucktaxi.presentation.models.ChatPresent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtChatVC
import java.util.*

/**
 * Created by root on 02.06.17.
 */
class ChatFragment : BaseParentFragment<FmtChatVC>() {

    companion object {
        val CHAT_FRAGMENT_ID: String = "main.chat_fragment"
    }

    @BindView(R.id.fmt_chat_rv_chat)
    lateinit var mRvData: RecyclerView

    private var mStickyBinder = object : SubsidaryBinder<ChatPresent> {
        override fun bind(view: View, data: ChatPresent) {
            (view.findViewById(R.id.v_chat_sticky_header_tv_text) as TextView).setText(data.date)
        }
    }

    private var mStickyPredicat = object : StickyPredicat<ChatPresent> {
        override fun call(data: ChatPresent): Long {
            return 0
        }
    }

    private var mStickyHeader: SubsidaryHolder<ChatPresent, FmtChatVC>? = null
    private var mAdapter: StickyRvAdapter<ChatPresent, FmtChatVC>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_chat, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtChatVC(this)
        mRvData.setLayoutManager(LinearLayoutManager(getContext()))
        mStickyHeader = SubsidaryHolder<ChatPresent, FmtChatVC>(getContext(), R.layout.v_chat_sticky_header, mStickyBinder)
        mAdapter = StickyRvAdapter<ChatPresent, FmtChatVC>(mStickyHeader!!, ChatHolder(getContext(), mViewController), mStickyPredicat)
        mRvData.addItemDecoration(StickyHeaderDecoration(mAdapter))
        mRvData.adapter = mAdapter
        test()
    }

    override fun onResume() {
        super.onResume()
        getToolbar().setTitle(getString(R.string.chat))
    }

    private fun test() {
        var list: ArrayList<ChatPresent> = ArrayList<ChatPresent>()
        list.add(ChatPresent(0, "Привет!", "22.05.2017"))
        list.add(ChatPresent(1, "Здравствуйте", "22.05.2017 в 18:02"))
        mAdapter?.loadData(list)
    }
}