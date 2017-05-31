package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import de.hdodenhof.circleimageview.CircleImageView
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.adapters.binders.NotificationBinder
import v_aniskin.com.trucktaxi.presentation.models.NotificationPresent
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtHomeVC

/**
 * Created by root on 26.05.17.
 */
class HomeFragment : BaseParentFragment<FmtHomeVC>() {

    companion object {
        val HOME_FRAGMENT_ID: String = "main.home_fragment"
    }

    @BindView(R.id.fmt_home_iv_avatar)
    lateinit var mIvAvatar: CircleImageView
    @BindView(R.id.fmt_home_ll_notifications)
    lateinit var mLlNotifications: LinearLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_home, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtHomeVC(this)
        test()
    }

    override fun onResume() {
        super.onResume()
        getBaseActivity<BaseActivity<*>>().getBottomNavigation()?.visibility = View.VISIBLE
        getToolbar().setTitle(getString(R.string.to_home))
    }

    fun test() {
        mLlNotifications.removeAllViews()
        mLlNotifications.addView(NotificationBinder(context).bind(NotificationPresent("Заказ №1 ул. Профсоюзная - ул. Моховая", "Ожидается подтверждение"), mLlNotifications))
        mLlNotifications.addView(NotificationBinder(context).bind(NotificationPresent("Заказ №2 ул. Комсомольская - ул. Набережная", "Ожидается подтверждение"), mLlNotifications))
        mLlNotifications.addView(NotificationBinder(context).bind(NotificationPresent("Заказ №3 ул. Демократическая - ул. Новоселова", "Ожидается подтверждение"), mLlNotifications))
        mIvAvatar.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.test_rider))
    }
}