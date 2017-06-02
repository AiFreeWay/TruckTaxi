package v_aniskin.com.trucktaxi.presentation.adapters.holders

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.models.ChatPresent
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtChatVC

/**
 * Created by root on 02.06.17.
 */
class ChatHolder: BaseHolder<ChatPresent, FmtChatVC> {

    @BindView(R.id.v_chat_holder_cv_body)
    lateinit var mCvBody: CardView
    @BindView(R.id.v_chat_holder_tv_text)
    lateinit var mTvText: TextView

    constructor(context: Context, viewController: FmtChatVC?) : super(context, viewController) {}

    constructor(view: View, viewController: FmtChatVC?) : super(view, viewController) {
        ButterKnife.bind(this, mView)
    }

    override fun create(viewGroup: ViewGroup): BaseHolder<ChatPresent, FmtChatVC> {
        val view = viewInflater(viewGroup, R.layout.v_chat_holder)
        return ChatHolder(view, mViewController)
    }

    override fun bind(dataModel: ChatPresent) {
        mTvText.setText(dataModel.text)
        if (dataModel.type == ChatPresent.STATE_ME) {
            mCvBody.setCardBackgroundColor(Color.WHITE)
            (mCvBody.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.LEFT
        } else {
            mCvBody.setCardBackgroundColor(ContextCompat.getColor(mView.context, R.color.mercury))
            (mCvBody.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.RIGHT
        }
    }
}