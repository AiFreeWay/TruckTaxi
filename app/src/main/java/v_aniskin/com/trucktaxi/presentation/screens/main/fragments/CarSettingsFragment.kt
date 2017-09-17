package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtCarSettingsVC
import java.io.File
import android.graphics.BitmapFactory
import android.graphics.Bitmap



/**
 * Created by root on 03.06.17.
 */
class CarSettingsFragment: BaseParentFragment<FmtCarSettingsVC>() {

    @BindView(R.id.fmt_car_settings_tv_car_model)
    lateinit var mTvCarModel: TextView
    @BindView(R.id.fmt_car_settings_tv_car_type)
    lateinit var mTvCarType: TextView
    @BindView(R.id.fmt_car_settings_tv_car_number)
    lateinit var mTvCarNumber: TextView

    @BindView(R.id.fmt_home_iv_photo1)
    lateinit var mIvPhoto1: ImageView
    @BindView(R.id.fmt_home_tv_photo1_edit)
    lateinit var mTvPhoto1: TextView
    @BindView(R.id.fmt_home_iv_photo2)
    lateinit var mIvPhoto2: ImageView
    @BindView(R.id.fmt_home_tv_photo2_edit)
    lateinit var mTvPhoto2: TextView
    @BindView(R.id.fmt_home_iv_photo3)
    lateinit var mIvPhoto3: ImageView
    @BindView(R.id.fmt_home_tv_photo3_edit)
    lateinit var mTvPhoto3: TextView
    @BindView(R.id.fmt_home_iv_interier)
    lateinit var mIvInterier: ImageView
    @BindView(R.id.fmt_home_tv_interier_edit)
    lateinit var mTvInterierEdit: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_car_settings, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtCarSettingsVC(this)
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
        mTvCarModel.setText(checkCarParamOnEmpty(profile.mMainCarModel))
        mTvCarType.setText(checkCarParamOnEmpty(profile.mMainCarType))
        mTvCarNumber.setText(checkCarParamOnEmpty(profile.mMainCarNumber))

        loadImage(ImageType.CAR_PHOTO_1, mIvPhoto1, mTvPhoto1, "")
        loadImage(ImageType.CAR_PHOTO_2, mIvPhoto2, mTvPhoto2, "")
        loadImage(ImageType.CAR_PHOTO_3, mIvPhoto3, mTvPhoto3, "")
        loadImage(ImageType.CAR_PHOTO_INTERIER, mIvInterier, mTvInterierEdit, "")
    }

    fun loadImage(imageType: Int, file: File) {
        when(imageType) {
            ImageType.CAR_PHOTO_1 -> loadFileToImage(mIvPhoto1, file)
            ImageType.CAR_PHOTO_2 -> loadFileToImage(mIvPhoto2, file)
            ImageType.CAR_PHOTO_3 -> loadFileToImage(mIvPhoto3, file)
            ImageType.CAR_PHOTO_INTERIER -> loadFileToImage(mIvInterier, file)
        }
    }

    private fun checkCarParamOnEmpty(param: String?): String {
        return if (!TextUtils.isEmpty(param)) param!! else getString(R.string.not_define)
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