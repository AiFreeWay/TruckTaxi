package v_aniskin.com.trucktaxi.presentation.adapters.addons

import android.support.v4.app.Fragment

/**
 * Created by root on 13.12.16.
 */
class ViewPagerItemContainer {

    var fragment: Fragment? = null
    var tabTitleResource: Int = 0
    var data: Any? = null

    constructor() {}

    constructor(tabTitleResource: Int, fragment: Fragment) {
        this.fragment = fragment
        this.tabTitleResource = tabTitleResource
    }

    constructor(tabTitleResource: Int, fragment: Fragment, data: Any) {
        this.fragment = fragment
        this.tabTitleResource = tabTitleResource
        this.data = data
    }
}
