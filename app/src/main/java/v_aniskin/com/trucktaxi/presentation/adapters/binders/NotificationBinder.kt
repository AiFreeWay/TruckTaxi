package v_aniskin.com.trucktaxi.presentation.adapters.binders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.models.NotificationPresent

/**
 * Created by root on 27.05.17.
 */
class NotificationBinder(mContext: Context) : BaseBinder<NotificationPresent>(mContext) {

    override fun bind(data: NotificationPresent, viewGroup: ViewGroup): View {
        mView = inflate(R.layout.v_notification, viewGroup)
        (mView.findViewById(R.id.v_notification_tv_order_text) as TextView).setText(data.text)
        (mView.findViewById(R.id.v_notification_tv_order_action) as TextView).setText(data.action)
        return mView
    }

}