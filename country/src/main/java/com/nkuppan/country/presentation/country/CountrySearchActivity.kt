package com.nkuppan.country.presentation.country

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nkuppan.country.R
import com.nkuppan.country.utils.RequestParam

/**
 * Created by ancientinc on 2019-07-06.
 **/
class CountrySearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_search)

        val countryListFragment = CountryListFragment()
        countryListFragment.countrySelection = {
            setResult(
                Activity.RESULT_OK,
                Intent().apply {
                    putExtra(RequestParam.SELECTED_VALUE, it)
                }
            )
            finish()
        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.place_holder, countryListFragment)
            .commitAllowingStateLoss()
    }
}