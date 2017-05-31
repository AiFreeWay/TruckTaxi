package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.SwitchCompat
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseActivity
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseParentFragment
import v_aniskin.com.trucktaxi.presentation.screens.main.view_controllers.FmtAuthVC

/**
 * Created by root on 25.05.17.
 */
class AuthFragment: BaseParentFragment<FmtAuthVC>() {

    companion object {
        val AUTH_FRAGMENT_ID: String = "main.auth_fragment"
    }

    @BindView(R.id.fmt_auth_swt_show_password)
    lateinit var mSwtShowPassword: SwitchCompat
    @BindView(R.id.fmt_auth_btn_enter)
    lateinit var mBtnEnter: AppCompatButton
    @BindView(R.id.fmt_auth_et_password)
    lateinit var mEtPassword: EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_auth, container, false)
        ButterKnife.bind(this, view)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewController = FmtAuthVC(this)
        getToolbar().setTitle(getString(R.string.autorization))

        mSwtShowPassword.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                mEtPassword.setTransformationMethod(null)
            else
                mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
            mEtPassword.setSelection(mEtPassword.getText().length)
        }

        mBtnEnter.setOnClickListener { mViewController?.auth() }
    }

    override fun onResume() {
        super.onResume()
        getBaseActivity<BaseActivity<*>>().getBottomNavigation()?.visibility = View.GONE
    }
}