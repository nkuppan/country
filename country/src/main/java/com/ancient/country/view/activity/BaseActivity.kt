package com.ancient.country.view.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by ancientinc on 16/12/18.
 */

open class BaseActivity : AppCompatActivity() {

    protected fun attachThisFragment(aPlaceHolderId: Int, aFragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(aPlaceHolderId, aFragment)
                .commitNow()
    }
}
