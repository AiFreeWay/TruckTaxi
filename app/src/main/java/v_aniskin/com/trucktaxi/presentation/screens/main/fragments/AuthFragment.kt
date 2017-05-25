package v_aniskin.com.trucktaxi.presentation.screens.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import v_aniskin.com.trucktaxi.R
import v_aniskin.com.trucktaxi.presentation.screens.common.BaseFragment

/**
 * Created by root on 25.05.17.
 */
class AuthFragment: BaseFragment() {

    companion object {
        val AUTH_FRAGMENT_ID: String = "main.auth_fragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.fmt_auth, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}