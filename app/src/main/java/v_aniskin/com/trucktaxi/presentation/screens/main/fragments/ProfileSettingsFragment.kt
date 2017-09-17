package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.application.utils.ImageType
import v_aniskin.com.trucktaxi.application.utils.Logger
import v_aniskin.com.trucktaxi.domain.models.Profile
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtProfileSettingsVC
import java.io.File

/**
 * Created by root on 03.06.17.
 */
class ProfileSettingsFragment: BaseParentFragment<FmtProfileSettingsVC>() {

    @BindView(R.id.fmt_home_iv_avatar)
    lateinit var mIvAvatar: ImageView
    @BindView(R.id.fmt_home_tv_name)
    lateinit var mTvName: TextView
    @BindView(R.id.fmt_home_tv_phone)
    lateinit var mTvPhone: TextView
    @BindView(R.id.fmt_home_tv_email)
    lateinit var mTvEmail: TextView

    @BindView(R.id.fmt_home_iv_driver_rule)
    lateinit var mIvDriverRule: ImageView
    @BindView(R.id.fmt_home_tv_driver_rule_edit)
    lateinit var mTvDriverRuleEdit: TextView
    @BindView(R.id.fmt_home_iv_registration)
    lateinit var mIvRegistration: ImageView
    @BindView(R.id.fmt_home_tv_registration_edit)
    lateinit var mTvRegistrationEdit: TextView
    @BindView(R.id.fmt_home_iv_ptc)
    lateinit var mIvPtc: ImageView
    @BindView(R.id.fmt_home_tv_ptc_edit)
    lateinit var mTvPtcEdit: TextView
    @BindView(R.id.fmt_home_iv_osago)
    lateinit var mIvOsago: ImageView
    @BindView(R.id.fmt_home_tv_osago_edit)
    lateinit var mTvOsagoEdit: TextView

    @BindView(R.id.fmt_home_btn_exit)
    lateinit var mBtnExit: Button

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_profile_settings, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtProfileSettingsVC(this)
    }

    override fun onStart() {
        super.onStart()
        mViewController?.start()
    }

    override fun onStop() {
        super.onStop()
        mViewController?.stop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == Activity.RESULT_OK) {
            try {
                if (ImageType.isImageType(requestCode)) {
                    val imageUri = intent!!.data
                    mViewController!!.loadImage(requestCode, File(imageUri.toString()))
                }
            } catch (e: Exception) {
                Logger.logError(e)
            }
        }
    }

    fun loadProfile(profile: Profile) {
        Picasso.with(context)
                .load(profile.mPhoto)
                .error(R.drawable.avatar_big)
                .placeholder(R.drawable.avatar_big)
                .into(mIvAvatar)

        val name: String = profile.mFirstName+" "+profile.mLastName
        mTvName.setText(name)
        mTvPhone.setText("")
        mTvEmail.setText(profile.mEmail)

        loadImage(ImageType.IMAGE_DRIVER_RULE, mIvDriverRule, mTvDriverRuleEdit, "")
        loadImage(ImageType.IMAGE_REGISTRATION, mIvRegistration, mTvRegistrationEdit, "")
        loadImage(ImageType.IMAGE_PTC, mIvPtc, mTvPtcEdit, "")
        loadImage(ImageType.IMAGE_OSAGO, mIvOsago, mTvOsagoEdit, "")

        mBtnExit.setOnClickListener {
            AlertDialog.Builder(context)
                    .setTitle("Вы хотите выйти?")
                    .setPositiveButton("Да", { a, b ->
                        mViewController!!.exit()
                        a.dismiss() })
                    .setNegativeButton("Нет", { a, b -> a.dismiss()})
                    .setCancelable(true)
                    .show()
        }
    }

    fun loadImage(imageType: Int, file: File) {
        when(imageType) {
            ImageType.IMAGE_DRIVER_RULE -> loadFileToImage(mIvDriverRule, file)
            ImageType.IMAGE_REGISTRATION -> loadFileToImage(mIvRegistration, file)
            ImageType.IMAGE_PTC -> loadFileToImage(mIvPtc, file)
            ImageType.IMAGE_OSAGO -> loadFileToImage(mIvOsago, file)
        }
    }

    private fun loadImage(imageType: Int, imageView: ImageView, editControllerView: TextView, url: String) {
        if (!TextUtils.isEmpty(url)) {
            imageView.visibility = View.VISIBLE
            Picasso.with(context)
                    .load(url)
                    .into(imageView)
        } else
            imageView.visibility = View.GONE

        editControllerView.setOnClickListener { getPicture(imageType) }
    }

    private fun getPicture(imageType: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, imageType)
    }

    private fun loadFileToImage(imageView: ImageView, file: File) {
        val bitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
        imageView.setImageBitmap(bitmap)
    }
}