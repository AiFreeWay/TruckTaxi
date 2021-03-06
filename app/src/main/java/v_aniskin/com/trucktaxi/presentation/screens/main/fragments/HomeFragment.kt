package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.os.Bundle
import android.support.v7.widget.SwitchCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.DateMapper
import v_aniskin.com.trucktaxi.application.utils.WorkStates
import v_aniskin.com.trucktaxi.domain.models.Notification
import v_aniskin.com.trucktaxi.domain.models.Profile
import v_aniskin.com.trucktaxi.presentation.adapters.binders.NotificationBinder
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

    @BindView(R.id.fmt_home_tv_name)
    lateinit var mTvName: TextView
    @BindView(R.id.fmt_home_rb_rate)
    lateinit var mRating: RatingBar
    @BindView(R.id.fmt_home_tv_rate)
    lateinit var mTvRating: TextView
    @BindView(R.id.fmt_home_tv_date_of_execution)
    lateinit var mTvDateOfExecution: TextView
    @BindView(R.id.fmt_home_tv_car_model)
    lateinit var mTvCarModel: TextView
    @BindView(R.id.fmt_home_tv_car_type)
    lateinit var mTvCarType: TextView
    @BindView(R.id.fmt_home_tv_car_number)
    lateinit var mTvCarNumber: TextView
    @BindView(R.id.fmt_home_swt_on_work)
    lateinit var mSwtOnWork: SwitchCompat
    @BindView(R.id.fmt_home_tv_notification)
    lateinit var mTvNotifications: TextView
    @BindView(R.id.fmt_home_iv_avatar)
    lateinit var mIvAvatar: CircleImageView
    @BindView(R.id.fmt_home_ll_notifications)
    lateinit var mLlNotifications: LinearLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_home, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtHomeVC(this)
    }

    override fun onResume() {
        super.onResume()
        getBaseActivity<BaseActivity<*>>().getBottomNavigation()?.visibility = View.VISIBLE
        getToolbar().setTitle(getString(R.string.to_home))
    }

    override fun onStart() {
        super.onStart()
        mViewController?.start()
    }

    override fun onStop() {
        super.onStop()
        mViewController?.stop()
    }

    fun lockSwtOnWork() {
        mSwtOnWork.isEnabled = false
    }

    fun unlockSwtOnWork() {
        mSwtOnWork.isEnabled = true
    }

    fun revertSwtOnWork() {
        mSwtOnWork.isChecked = !mSwtOnWork.isChecked
    }

    fun loadProfile(profile: Profile) {
        Picasso.with(context)
                .load(profile.mPhoto)
                .error(R.drawable.avatar)
                .placeholder(R.drawable.avatar)
                .into(mIvAvatar)

        val name: String = profile.mFirstName+" "+profile.mLastName
        mTvName.setText(name)
        mRating.rating = profile.mRate!!.toFloat()
        val rating = getString(R.string.rating)+" "+profile.mRate
        mTvRating.setText(rating)
        val dateOfExecution = getString(R.string.date_of_execution)+" "+DateMapper.mapDate(profile.mFormalizDate!!.toLong())
        mTvDateOfExecution.setText(dateOfExecution)
        mTvCarModel.setText(checkCarParamOnEmpty(profile.mMainCarModel))
        mTvCarType.setText(checkCarParamOnEmpty(profile.mMainCarType))
        mTvCarNumber.setText(checkCarParamOnEmpty(profile.mMainCarNumber))
        mSwtOnWork.isChecked =  TextUtils.equals(profile.mStatus, WorkStates.WORK_STATE_ON_WORK)
        mSwtOnWork.setOnCheckedChangeListener { compoundButton, b ->
            val workState = if (b) WorkStates.WORK_STATE_ON_WORK else WorkStates.WORK_STATE_ON_REST
            mViewController?.editProfile(workState)
        }
    }

    fun loadNotifications(notifications: List<Notification>) {
        val notificationsTitle: String
        if (notifications.isNotEmpty())
            notificationsTitle = getString(R.string.notifications_title)+" "+notifications.size
        else
            notificationsTitle = getString(R.string.no_new_notifcations)
        mTvNotifications.setText(notificationsTitle)

        mLlNotifications.removeAllViews()
        notifications.forEach {
            notification ->  mLlNotifications.addView(NotificationBinder(context).bind(notification, mLlNotifications))
        }
    }

    private fun checkCarParamOnEmpty(param: String?): String {
        return if (!TextUtils.isEmpty(param)) param!! else getString(R.string.not_define)
    }
}