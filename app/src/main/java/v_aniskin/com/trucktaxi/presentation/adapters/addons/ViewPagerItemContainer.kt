package v_aniskin.com.trucktaxi.presentation.adapters.addons

import android.support.v4.app.Fragment

/**
 * Created by root on 13.12.16.
 */
class ViewPagerItemContainer {

    var fragment: Fragment? = null
    var tabTitle: String = ""
    var data: Any? = null

    constructor() {}

    constructor(tabTitle: String, fragment: Fragment) {
        this.fragment = fragment
        this.tabTitle = tabTitle
    }

    constructor(tabTitle: String, fragment: Fragment, data: Any) {
        this.fragment = fragment
        this.tabTitle = tabTitle
        this.data = data
    }
}
